package com.akoya.codex.upgrader.model;

import com.akoya.codex.upgrader.JsonSerializable;
import com.akoya.codex.upgrader.MicroscopeTypeEnum;
import com.akoya.codex.upgrader.dto.ChannelDto;
import com.akoya.codex.upgrader.dto.CycleDto;
import com.akoya.codex.upgrader.utils.Path;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Modifier;
import java.util.*;


/**
 *
 * @author Vishal
 */
public class Experiment implements JsonSerializable {
    private String experimentId; //from server
    private String userName;
    private String name;
    private String date;
    private String projName;
    private String codex_instrument;
    private MicroscopeTypeEnum microscope;
    private String deconvolution;
    private String objectiveType;
    private Integer magnification;
    private double numerical_aperture;
    private double per_pixel_XY_resolution;
    private double z_pitch;

    private Integer num_z_planes;
    /**
     * Duplicates {@link #num_z_planes} for compatibility
     */
    private Integer numPlanes;

    private String channel_arrangement;
    private String[] channel_names;
    private int[] emission_wavelengths;
    private int drift_comp_channel;
    private int driftCompReferenceCycle;
    private int best_focus_channel;
    private int bestFocusReferenceCycle;
    private int num_cycles;
    private int cycle_upper_limit;
    private int cycle_lower_limit;
    private int[] regIdx;
    private String[] region_names;
    private String tiling_mode;
    private int region_width;
    private int region_height;
    private int tile_overlap_X;
    private int tile_overlap_Y;
    private boolean HandEstain;
    private int tile_height;
    private int tile_width;
    private int[] readout_channels;
    private boolean optionalFocusFragment;
    private int focusing_offset;
    private boolean bgSub;
    private boolean useBleachMinimizingCrop;
    private boolean useBlindDeconvolution;
    private String experimentRootPath;
    private String experimentRootFolderName;
    private String outputRootPath;
    private String outputPath;
    private String id;
    private List<CycleDto> cycles;
    private transient Path path;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public List<CycleDto> getCycles() {
        return cycles;
    }

    public void setCycles(List<CycleDto> cycles) {
        this.cycles = cycles;
        if (cycles != null) {
            //Set default marker display names
            for (CycleDto cycleDto : cycles) {
                for (ChannelDto channelDto : cycleDto.getChannels()) {
                    if (StringUtils.isEmpty(channelDto.getMarkerDisplayName())) {
                        channelDto.setMarkerDisplayName(channelDto.getMarkerName());
                    }
                }
            }
        }
    }

    private List<Region> regions;

    public List<Region> getRegions() {
        if (regions == null) {
            regions = new ArrayList<>();
            for (int regIdx : getRegIdx()) {
                regions.add(new Region(regIdx, region_height, region_width));
            }
        }
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return absolute path for experiment output path
     */
    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    private static transient HashMap<String, String> projectNameCache = new HashMap<>();
    private static transient final MicroscopeTypeEnum[] microscopeTypes = new MicroscopeTypeEnum[]{MicroscopeTypeEnum.KEYENCE, MicroscopeTypeEnum.ZEISS, MicroscopeTypeEnum.LEICA};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCodex_instrument() {
        return codex_instrument;
    }

    public void setCodex_instrument(String codex_instrument) {
        this.codex_instrument = codex_instrument;
    }

    public MicroscopeTypeEnum getMicroscope() {
        return microscope;
    }

    public void setMicroscope(MicroscopeTypeEnum microscope) {
        this.microscope = microscope;
    }

    public String getDeconvolution() {
        return deconvolution;
    }

    public void setDeconvolution(String deconvolution) {
        this.deconvolution = deconvolution;
    }

    public Integer getMagnification() {
        return magnification;
    }

    public void setMagnification(Integer magnification) {
        this.magnification = magnification;
    }

    public double getNumerical_aperture() {
        return numerical_aperture;
    }

    public void setNumerical_aperture(double numerical_aperture) {
        this.numerical_aperture = numerical_aperture;
    }

    public double getPer_pixel_XY_resolution() {
        return per_pixel_XY_resolution;
    }

    public void setPer_pixel_XY_resolution(double per_pixel_XY_resolution) {
        this.per_pixel_XY_resolution = per_pixel_XY_resolution;
    }

    public double getZ_pitch() {
        return z_pitch;
    }

    public void setZ_pitch(double z_pitch) {
        this.z_pitch = z_pitch;
    }

    public Integer getNum_z_planes() {
        return num_z_planes != null ? num_z_planes : numPlanes;
    }

    public void setNum_z_planes(Integer num_z_planes) {
        this.num_z_planes = num_z_planes;
    }

    public String getChannel_arrangement() {
        return channel_arrangement;
    }

    public void setChannel_arrangement(String channel_arrangement) {
        this.channel_arrangement = channel_arrangement;
    }

    public String[] getChannel_names() {
        return channel_names;
    }

    public void setChannel_names(String[] channel_names) {
        this.channel_names = channel_names;
    }

    public int[] getEmission_wavelengths() {
        return emission_wavelengths;
    }

    public void setEmission_wavelengths(int[] emission_wavelengths) {
        this.emission_wavelengths = emission_wavelengths;
    }

    public int getDrift_comp_channel() {
        return drift_comp_channel;
    }

    public void setDrift_comp_channel(int drift_comp_channel) {
        this.drift_comp_channel = drift_comp_channel;
    }

    public int getBest_focus_channel() {
        return best_focus_channel;
    }

    public void setBest_focus_channel(int best_focus_channel) {
        this.best_focus_channel = best_focus_channel;
    }

    public int getNum_cycles() {
        this.num_cycles = this.getCycle_upper_limit() - this.getCycle_lower_limit() + 1;
        return num_cycles;
    }

    public int getCycle_upper_limit() {
        return cycle_upper_limit;
    }

    public void setCycle_upper_limit(int cycle_upper_limit) {
        this.cycle_upper_limit = cycle_upper_limit;
    }

    public int getCycle_lower_limit() {
        return cycle_lower_limit;
    }

    public void setCycle_lower_limit(int cycle_lower_limit) {
        this.cycle_lower_limit = cycle_lower_limit;
    }

    public int[] getRegIdx() {
        if (regIdx == null) {
            List<Region> regions = getRegions();
            regIdx = regions.stream()
                    .mapToInt(Region::getIndex)
                    .toArray();
        }
        return regIdx;
    }

    public void setRegIdx(int[] regIdx) {
        this.regIdx = regIdx;
    }

    public String[] getRegion_names() {
        if (region_names == null) {
            List<Region> regions = getRegions();
            region_names = regions.stream()
                    .map(r -> String.valueOf("Region " + r.getIndex()))
                    .toArray(String[]::new);
        }
        return region_names;
    }

    public void setRegion_names(String[] region_names) {
        this.region_names = region_names;
    }

    public String getTiling_mode() {
        return tiling_mode;
    }

    public void setTiling_mode(String tiling_mode) {
        this.tiling_mode = tiling_mode;
    }

    public int getRegionWidthInTiles() {
        return region_width;
    }

    public void setRegion_width(int region_width) {
        this.region_width = region_width;
    }

    public int getRegionHeightInTiles() {
        return region_height;
    }

    public void setRegion_height(int region_height) {
        this.region_height = region_height;
    }

    public int getTile_overlap_X() {
        return tile_overlap_X;
    }

    public void setTile_overlap_X(int tile_overlap_X) {
        this.tile_overlap_X = tile_overlap_X;
    }

    public int getTile_overlap_Y() {
        return tile_overlap_Y;
    }

    public void setTile_overlap_Y(int tile_overlap_Y) {
        this.tile_overlap_Y = tile_overlap_Y;
    }

    public int[] getReadout_channels() {
        int k = 0;
        for (int i = 1; i <= this.getChannel_names().length; i++) {
            if (i == this.getDrift_comp_channel()) {
                continue;
            }
            readout_channels[k++] = i;
        }
        return readout_channels;
    }

    public String getObjectiveType() {
        return objectiveType;
    }

    public void setObjectiveType(String objectiveType) {
        this.objectiveType = objectiveType;
    }

    public boolean isHandEstain() {
        return HandEstain;
    }

    public void setHandEstain(boolean handEstain) {
        HandEstain = handEstain;
    }

    public int getTile_height() {
        return tile_height;
    }

    public void setTile_height(int tile_height) {
        this.tile_height = tile_height;
    }

    public int getTile_width() {
        return tile_width;
    }

    public void setTile_width(int tile_width) {
        this.tile_width = tile_width;
    }

    public int getDriftCompReferenceCycle() {
        return driftCompReferenceCycle;
    }

    public void setDriftCompReferenceCycle(int driftCompReferenceCycle) {
        this.driftCompReferenceCycle = driftCompReferenceCycle;
    }

    public int getBestFocusReferenceCycle() {
        return bestFocusReferenceCycle;
    }

    public void setBestFocusReferenceCycle(int bestFocusReferenceCycle) {
        this.bestFocusReferenceCycle = bestFocusReferenceCycle;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public boolean isOptionalFocusFragment() {
        return optionalFocusFragment;
    }

    public void setOptionalFocusFragment(boolean optionalFocusFragment) {
        this.optionalFocusFragment = optionalFocusFragment;
    }

    public int getFocusing_offset() {
        return focusing_offset;
    }

    public void setFocusing_offset(int focusing_offset) {
        this.focusing_offset = focusing_offset;
    }

    public boolean isBgSub() {
        return bgSub;
    }

    public void setBgSub(boolean bgSub) {
        this.bgSub = bgSub;
    }

    public boolean isUseBleachMinimizingCrop() {
        return useBleachMinimizingCrop;
    }

    public void setUseBleachMinimizingCrop(boolean useBleachMinimizingCrop) {
        this.useBleachMinimizingCrop = useBleachMinimizingCrop;
    }

    public boolean isUseBlindDeconvolution() {
        return useBlindDeconvolution;
    }

    public void setUseBlindDeconvolution(boolean useBlindDeconvolution) {
        this.useBlindDeconvolution = useBlindDeconvolution;
    }

    public static HashMap<String, String> getProjectNameCache() {
        return projectNameCache;
    }

    public static void setProjectNameCache(HashMap<String, String> projectNameCache) {
        Experiment.projectNameCache = projectNameCache;
    }

    public static MicroscopeTypeEnum[] getMicroscopeTypes() {
        return microscopeTypes;
    }

    public String getExperimentRootPath() {
        return experimentRootPath;
    }

    public void setExperimentRootPath(String experimentRootPath) {
        this.experimentRootPath = experimentRootPath;
    }

    public String getExperimentRootFolderName() {
        return experimentRootFolderName;
    }

    public void setExperimentRootFolderName(String experimentRootFolderName) {
        this.experimentRootFolderName = experimentRootFolderName;
        if (StringUtils.isEmpty(name)) {
            //Use name of root folder only if experiment name was empty
            setName(experimentRootFolderName);
        }
    }

    public String getOutputRootPath() {
        return outputRootPath;
    }

    public void setOutputRootPath(String outputRootPath) {
        this.outputRootPath = outputRootPath;
    }

    public String getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(String experimentId) {
        this.experimentId = experimentId;
    }

    public static Experiment loadFromJSON(File f) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(f));
        Experiment exp = gson.fromJson(reader, Experiment.class);
        return exp;
    }

    @Override
    public String toJSON() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT) // include static
                .create();
        return gson.toJson(this).replaceAll(",", ",\n");
    }

    public Integer getNumPlanes() {
        return numPlanes != null ? numPlanes : num_z_planes;
    }

    public void setNumPlanes(Integer numPlanes) {
        this.numPlanes = numPlanes;
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(Experiment.class);
    private static final int MAX_RANGE_IN_PERCENT = 99;
    public static final String POPULATIONS_FILE_NAME_PART = "populations-";
    public static final String REGION_PREFIX = "reg%03d_";
    private static final String DELAUNAY_GRAPH_FOR_REGION = "delaunay_graph_region";
    public static final String COMPENSATED_STR = "compensated";
    public static final String UNCOMPENSATED_STR = "uncompensated";

    public final static boolean COMPENSATED = true;
    public static final int UNSET_COLUMN = -1;
    public static final String FCS_POSTFIX = ".fcs";
    private static final String CLUSTER_ID_COLUMN_NAME_PART = "cluster";
    private static final String CELL_UID_DELIMITER = "_";
    public static final String COLUMN_NAMES_DELIMITER = ":";
    private static final String X_COLUMN_NAME = "x";
    private static final String Y_COLUMN_NAME = "y";
    private static final String Z_COLUMN_NAME = "z";

    private String processingJobId = null;
    private String segmentationJobId = null;
    public String selectedSegmRun = null;
    private String selectedClusteringRun = null;

    private int selectedRegion;
    private Map<String, Integer> cellUidMap;
    private int xOriginalCol = UNSET_COLUMN, yOriginalCol = UNSET_COLUMN, zOriginalCol = UNSET_COLUMN;

    //Sorted array of values from all clusters from current clustering run, from all markers
    private double[][] quantileMap;

    private Map<Integer, double[]> clusterPerFrequenciesInRegion;

    private Map<Integer, Integer> cellsClustersMap; //cellRow in original Dataset -> ClusterID in selected clustering run
    private String processedPath = "processed";
    private String tiles = "tiles";

    private File root;
    private final Object mountMonitor = new Object();

    public String getProcessedPath() {
        return processedPath;
    }

    public void setProcessedPath(String processedPath) {
        this.processedPath = processedPath;
    }

    public File getExperimentRootFolder() {
        return new File(this.experimentRootPath);
    }

    public File getBaseFolder() {
        return new File(concatPath(getExperimentOutputPath(), processedPath));
    }

    public File getTilesBaseFolder() {
        return new File(concatPath(getExperimentOutputPath(), processedPath, tiles));
    }

    public File getSegmBaseFolder() {
        String path = concatPath(getExperimentOutputPath(), processedPath);
        return new File(path);
    }

    public String concatPath(String... pathArgs) {
        if (pathArgs.length < 2) {
            throw new IllegalArgumentException("Less than 2 arguments were provided to the concatPath function");
        }
        String path = pathArgs[0];
        for (int i = 1; i < pathArgs.length; i++) {
            path = FilenameUtils.concat(path, pathArgs[i]);
        }
        return path;
    }

    private synchronized String getExperimentOutputPath() {
//        File experimentOutputFolder = new File(this.getOutputPath());
//        if (!experimentOutputFolder.exists() && IJ.isMacOSX() && UserService.isSignedIn()) {
//            doRemount();
//        }
        return this.getOutputPath();
    }

//    public void doRemount() {
//        synchronized (mountMonitor) {
//            try {
//                //Due to a paths can be changed, obtain it from the server
//                LOGGER.debug("Connection to the share has been lost. Do remounting");
//                ExperimentDto dto = new ExperimentService().getExperimentById(exp.getId()).get();
//                Experiment experiment = ExperimentDto.convertToExperiment(dto);
//
//                MessageDialogs.showWarningDialog(null, Messages.SMB_CONNECTION_LOST, TITLE_MOUNT_POINTS);
//
//                MountPanel mountPanel = new MountPanel(experiment, panExperimentControl);
//                mountPanel.addWindowListener(new WindowAdapter() {
//                    @Override
//                    public void windowClosed(WindowEvent e) {
//                        try {
//                            validateNewMountPoint(mountPanel.getSelectedFolder());
//                        } catch (IllegalStateException exc) {
//                            LOGGER.error("Couldn't remount due to: ", exc);
//                        } finally {
//                            synchronized (mountMonitor) {
//                                LOGGER.debug("Notify all after remounting");
//                                mountMonitor.notifyAll();
//                            }
//                        }
//                    }
//                });
//                mountPanel.setVisible(true);
//                if (!SwingUtilities.isEventDispatchThread()) {
//                    LOGGER.debug("waiting for mounting");
//                    mountMonitor.wait();
//                }
//                LOGGER.debug("Finish remounting");
//            } catch (InterruptedException | ExecutionException e) {
//                Thread.currentThread().interrupt();
//                LOGGER.error("Exception during remounting: ", e);
//            }
//        }
//    }

    public String[] getSegmRunNames() {
        File baseFolder = getSegmBaseFolder();
        String[] emptyResult = {};
        if (baseFolder == null) {
            return emptyResult;
        } else {
            File[] folders = baseFolder.listFiles(file -> file.isDirectory() && !file.isHidden());
            return folders != null
                    ? Arrays.stream(folders).map(File::getName).toArray(String[]::new)
                    : emptyResult;
        }
    }

    public File getFCSBaseFolder() {
        return getFCSBaseFolder(selectedSegmRun, COMPENSATED, null);
    }

    public File getFCSBaseFolder(String segmRunName, boolean compensated, String gate) {
        if (gate == null) {
            return new File(FilenameUtils.concat(FilenameUtils.concat(
                    FilenameUtils.concat(getSegmBaseFolder().getAbsolutePath(), segmRunName), "FCS"),
                    (compensated ? COMPENSATED_STR : UNCOMPENSATED_STR)));
        } else {
            return new File("not implemented");
        }
    }

    public String getSelectedSegmRun() {
        return selectedSegmRun;
    }

    public void setSelectedSegmRun(String selectedSegmRun) {
        this.selectedSegmRun = selectedSegmRun;
    }
}
