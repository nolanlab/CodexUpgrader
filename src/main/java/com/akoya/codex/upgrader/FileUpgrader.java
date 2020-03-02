package com.akoya.codex.upgrader;


import com.akoya.codex.upgrader.model.Experiment;
import com.akoya.codex.upgrader.model.UpgradedExperiment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ij.IJ;
import ij.ImagePlus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Vishal
 */
public class FileUpgrader extends SwingWorker<Boolean, String> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public FileUpgrader() {
    }

    public Experiment experiment;
    private String BESTFOCUS_REGEX = "(reg[0-9]{3}_X[0-9]{2}_Y[0-9]{2})_Z([0-9]{2}).tif";
    private String TILE_REGEX = "(reg[0-9]{3}_X[0-9]{2}_Y[0-9]{2}).tif";

    private String SEGM_RUN_NAME = "segmented_upgraded";
    private String SEGM_COMPENSATED = ".*reg[0-9]{3}_.*Compensated.*";
    private String SEGM_UNCOMPENSATED = ".*reg[0-9]{3}_.*Uncompensated.*";
    private String SEGM_MASK_PNG = "regions_(reg[0-9]{3}_X[0-9]{2}_Y[0-9]{2})_Z[0-9]{2}.png";

    private String MASKS_FOLDER = "masks";

    @Override
    protected Boolean doInBackground() {
        return upgrade();
    }

    public boolean upgrade() {
        try {
            if (requiresUpgrade()) {
                publish("Processing Tiles");
                createProcessedTiles();

                publish("Creating bestFocus data");
                createProcessedTilesBestFocusJson();
                if(canUpgradeSegmRun()) {
                    publish("Processing Segmentation Run");
                    copyProcessedSegRunFiles();
                    createSegmConfigJson();
                }
            } else if (canUpgradeSegmRun()) {
                publish("Processing Segmentation Run");
                copyProcessedSegRunFiles();
                createSegmConfigJson();
            }
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean requiresUpgrade() {
        return !getBestFocusFile().exists();
    }

    public boolean canUpgradeSegmRun() {
        return filesMatching(SEGM_COMPENSATED).length > 0
                && filesMatching(SEGM_UNCOMPENSATED).length > 0
                && filesMatching(SEGM_MASK_PNG).length > 0
                && !requiresUpgrade();
    }

    public File getLegacyBestFocusFolder() {
        return new File(experiment.getExperimentRootFolder().getAbsolutePath() + File.separator + "bestFocus");
    }

    protected void createProcessedTileFolder() {
        if (!experiment.getTilesBaseFolder().exists()) {
            LOGGER.info("Creating folder: {}", experiment.getTilesBaseFolder().getAbsolutePath());
            experiment.getTilesBaseFolder().mkdirs();
        }
    }

    private void copyExperimentFiles() throws IOException {
        File oldExpJson = new File(experiment.getExperimentRootFolder() + File.separator + "Experiment.json");
        if(!oldExpJson.exists()) {
            throw new IllegalStateException("Experiment.json not found in the processed folder...");
        }

        // Upgrade experiment.json
        Experiment exp = Experiment.loadFromJSON(oldExpJson);
        UpgradedExperiment up = new UpgradedExperiment(exp);
        File newExpJson = new File(experiment.getBaseFolder() + File.separator + "experiment.json");
        up.saveToFile(newExpJson);

        // Copy channel names
        File chNames = new File(experiment.getExperimentRootFolder() + File.separator + "channelNames.txt");
        FileUtils.copyFileToDirectory(chNames, experiment.getBaseFolder());
    }

    public File getBestFocusFile() {
        String processingAbsPath = experiment.getBaseFolder().getAbsolutePath();
        return new File(FilenameUtils.concat(processingAbsPath, "bestFocus.json"));
    }

    public boolean createProcessedTilesBestFocusJson() {
        createProcessedTileFolder();
        File bfJ = getBestFocusFile();
        if (!bfJ.exists()) {
            try (Writer writer = new FileWriter(bfJ)) {
                String[] fileNames = Arrays.stream(getLegacyBestFocusFolder().listFiles()).map(ff -> ff.getName()).toArray(String[]::new);
                HashMap<String, Integer> map = getBestFocusMap(fileNames);
                Gson gson = new GsonBuilder().create();
                gson.toJson(map, writer);
                LOGGER.info("Creating file: {}", bfJ.getAbsolutePath());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public HashMap<String, Integer> getBestFocusMap(String[] files) {
        HashMap<String, Integer> bestFocusMap = new HashMap<>();
        for (String bestFocusFN : files) {
            if (bestFocusFN.matches(BESTFOCUS_REGEX)) {
                String key = bestFocusFN.replaceAll(BESTFOCUS_REGEX, "$1");
                Integer z = Integer.parseInt(bestFocusFN.replaceAll(BESTFOCUS_REGEX, "$2"));
                bestFocusMap.put(key, z);
            }
        }
        return bestFocusMap;
    }

    public File createTileFolder(String tileName) {
        File tileFolder = new File(experiment.getTilesBaseFolder().getAbsolutePath() + File.separator + tileName);
        LOGGER.info("Creating folder: {}", tileFolder.getAbsolutePath());
        tileFolder.mkdir();
        return tileFolder;
    }

    public void createImageSlice(ImagePlus image, File folder, String name, int c, int z, int t) {
        File sliceFile = new File(folder.getAbsolutePath() + File.separator + name);
        if (!sliceFile.exists()) {
            image.setPosition(c, z, t);
            ImagePlus slice = new ImagePlus(name, image.getProcessor());
            IJ.saveAsTiff(slice, sliceFile.getAbsolutePath());
            LOGGER.info("Creating file: {}", sliceFile.getAbsolutePath());
        } else {
            LOGGER.info("{} has already been created", sliceFile.getAbsolutePath());
        }
    }

    public void createProcessedTiles() throws IOException {
        createProcessedTileFolder();
        copyExperimentFiles();

        File[] tiles = filesMatching(TILE_REGEX);

        Integer totalFiles = tiles.length;
        Integer currentFile = 0;

        // TODO: 2019-01-29 ???
        int expectedNumFiles = experiment.getRegion_names().length * experiment.getRegionWidthInTiles() * experiment.getRegionHeightInTiles();

        if (totalFiles != expectedNumFiles) {
            throw new IllegalStateException("This experiment has unexpected number of tiles: " + totalFiles + " instead of " + expectedNumFiles + "\n Perhaps the experiment processing was incomplete");
        }


        for (File tile : tiles) {
            String tileName = tile.getName().replaceAll(TILE_REGEX, "$1");
            File tileFolder = createTileFolder(tileName);

            LOGGER.info("Opening file: {}", tile.getAbsolutePath());
            currentFile += 1;
            publish("Opening tile : " + tile.getName());

            publish("Progress:" + (100 * (currentFile)) / totalFiles);

            ImagePlus image = IJ.openImage(tile.getPath());

            Integer totalSlices = image.getNChannels() * image.getNFrames() * image.getNSlices();
            Integer currentSlice = 0;
            publish("Saving tile slices : " + tile.getName());

            for (int c = 1; c <= image.getNChannels(); c++) {
                for (int t = 1; t <= image.getNFrames(); t++) {
                    for (int z = 1; z <= image.getNSlices(); z++) {
                        String sliceName = String.format(tileName + "_t%03d_z%03d_c%03d.tif", t, z, c);
                        currentSlice += 1;
                        publish("Progress:" + (100 * (currentSlice)) / totalSlices);
                        createImageSlice(image, tileFolder, sliceName, c, z, t);
                    }
                }
            }
        }
    }

    public void copyProcessedSegRunFiles() throws IOException {
        String segmRunName = getNextSegmRunName();
        experiment.setSelectedSegmRun(segmRunName);

        copyProccessedFiles(SEGM_UNCOMPENSATED, experiment.getFCSBaseFolder(segmRunName, false, null));
        copyProccessedFiles(SEGM_COMPENSATED, experiment.getFCSBaseFolder(segmRunName, true, null));

        copySegmRunOverlayFiles(SEGM_MASK_PNG, segmRunName);
    }

    private String getNextSegmRunName() {
        if (experiment.getSegmBaseFolder().exists()) {
            String[] segmRunNames = experiment.getSegmRunNames();
            Integer run_number = 1;
            String candidateSegmRunName = SEGM_RUN_NAME + "_" + run_number;
            while (Arrays.asList(segmRunNames).indexOf(candidateSegmRunName) >= 0) {
                run_number += 1;
                candidateSegmRunName = SEGM_RUN_NAME + "_" + run_number;
            }
            return candidateSegmRunName;
        }
        return SEGM_RUN_NAME + "_1";
    }

    private void copyProccessedFiles(String regexp, File destDir) throws IOException {
        String message = "Copying FCS files ";
        File[] files = filesMatching(regexp);
        Integer totalFiles = files.length;
        Integer currentFile = 0;
        // Removed to avoid creating empty dirs if segmentation isn't present - uncommented this part
        if (!destDir.exists()){
            Files.createDirectories(destDir.toPath());
        }
        for (File file : files) {
            File dest = new File(destDir.getAbsolutePath() + File.separator + file.getName());
            if (!dest.exists()) {
                Files.copy(file.toPath(), dest.toPath());
            }
            currentFile += 1;
            publish(message + currentFile + " of " + totalFiles + " to " + destDir.getName());
        }
    }


    private void copySegmRunOverlayFiles(String regexp, String segmRunName) throws IOException {
        publish("Copying Overlay Masks files");
        File[] files = filesMatching(regexp);
        Integer totalFiles = files.length;
        Integer currentFile = 0;
        for (File file : files) {
            String filename = file.getName();
            String region = filename.replaceAll(SEGM_MASK_PNG, "$1");
            File destDir = new File(experiment.getSegmBaseFolder() + File.separator + segmRunName + File.separator + MASKS_FOLDER + File.separator + region);
            if (!destDir.exists()) {
                Files.createDirectories(destDir.toPath());
            }
            File dest = new File(destDir.getAbsolutePath() + File.separator + file.getName());
            if (!dest.exists()) {
                Files.copy(file.toPath(), dest.toPath());
            }
            currentFile += 1;
            publish("Progress:" + (100 * currentFile) / totalFiles);
        }
    }

    File[] filesMatching(String regex) {
        FilenameFilter fileNameFilter = (dir, name) -> name.matches(regex);
        return experiment.getExperimentRootFolder().listFiles(fileNameFilter);
    }

    public void createSegmConfigJson() {
        String configPath = experiment.getExperimentRootFolder() + File.separator + "config.txt";
        HashMap<String, Object> map = new HashMap<>();

        String line;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(configPath));
            while ((line = reader.readLine()) != null)
            {
                Object[] parts = line.split("=", 2);
                if (parts.length >= 2)
                {
                    String key = parts[0].toString();
                    Object value = parts[1];
                    if (value.toString().equalsIgnoreCase("true")) {
                        value = true;
                    } else if (value.toString().equalsIgnoreCase("false")) {
                        value = false;
                    } else {
                        value = Double.parseDouble(value.toString());
                    }
                    map.put(key, value);
                } else {
                    System.out.println("ignoring line: " + line);
                }
            }
            File segJ = new File(experiment.getBaseFolder() + File.separator + experiment.getSelectedSegmRun() +
                   File.separator + "segmentation.json");
            if (!segJ.exists()) {
                try (Writer writer = new FileWriter(segJ)) {
                    Gson gson = new GsonBuilder().create();
                    gson.toJson(map, writer);
                    LOGGER.info("Creating file: {}", segJ.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            MessageDialogs.showErrorDialog(null, Messages.NO_SEGMCONFIG_FOUND);
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }
}
