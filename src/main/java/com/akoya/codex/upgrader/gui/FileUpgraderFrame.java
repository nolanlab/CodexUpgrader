package com.akoya.codex.upgrader.gui;

import com.akoya.codex.upgrader.*;
import com.akoya.codex.upgrader.model.Experiment;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


/**
 *
 * @author Vishal
 */
public class FileUpgraderFrame extends JFrame {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUpgraderFrame.class);
    public static final String OUTPUT_FOLDER_NAME = "output";
    private FileUpgrader upgrader;
    private JButton upgradeFilesButton = new JButton("Upgrade Files");
    private JButton selectExpButton = new JButton("Select experiment");
    private JLabel expLabel = new JLabel();

    //TODO: remove these dependencies
    private Experiment experiment;
//    private PanExperimentControl panExpControl = new PanExperimentControl();

    public FileUpgraderFrame() {
        setTitle("CodexFileUpgrader");
        setSize(500, 500);
        setLocation(200, 200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));
        add(selectExpButton);
        add(upgradeFilesButton);
        add(expLabel);

        upgradeFilesButton.setEnabled(false);
        upgradeFilesButton.addActionListener(e -> startUpgrade());
        selectExpButton.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File experimentRoot = jfc.getSelectedFile();
                loadExperimentFromRootFolder(experimentRoot);
                expLabel.setText(experiment.getName());
                upgradeFilesButton.setEnabled(true);
            }
//            if (panExpControl.getEr() != null) {
//                experiment = panExpControl.getEr();
//                expLabel.setText(experiment.getExperiment().getName());
//                upgradeFilesButton.setEnabled(true);
//            }
        });
    }

    public void startUpgrade() {
        java.awt.Dialog d = null;
        JDialog dlgProgress = new JDialog(d, "Upgrading Files", true);
        dlgProgress.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 150, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 45, 300, 90);
        JLabel lblStatus = new JLabel("Working...");
        JProgressBar pbProgress = new JProgressBar(0, 100);
        pbProgress.setIndeterminate(true);
        dlgProgress.add(BorderLayout.NORTH, lblStatus);
        dlgProgress.add(BorderLayout.CENTER, pbProgress);
        dlgProgress.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // prevent the user from closing the dialog
        dlgProgress.setSize(500, 90);

        FileUpgrader upgrade = new FileUpgrader() {
            @Override
            protected void done() {
                boolean status;
                try {
                    // Retrieve the return value of doInBackground.
                    status = get();
                    if (status) {
                        lblStatus.setText("File upgrade successful");
                        pbProgress.setIndeterminate(false);
                        pbProgress.setValue(100);
                    } else {
                        lblStatus.setText("File upgrade error");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    lblStatus.setText("File upgrade interrupted");
                } catch (ExecutionException e) {
                    lblStatus.setText("File upgrade interrupted with exception");
                }
                dlgProgress.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//                configureOptions();
//                checkUpgrade();
            }

            protected void process(List<String> chunks) {
                {
                    for (String msg : chunks) {
                        if (msg.startsWith("Progress:")) {
                            pbProgress.setIndeterminate(false);
                            pbProgress.setValue((int) Double.parseDouble(msg.split(":")[1]));
                        } else {
                            pbProgress.setIndeterminate(true);
                            lblStatus.setText(msg);
                        }

                    }

                }
            }
        };
        upgrade.setExperiment(experiment);
        upgrade.execute();
        dlgProgress.setVisible(true);
    }

    private void checkUpgrade() {
        if (upgrader == null) {
            upgrader = new FileUpgrader();
            upgrader.setExperiment(experiment);
        }
        if (upgrader.requiresUpgrade()) {
            int selectedOption =
                    JOptionPane.showOptionDialog(null,
                            "This experiment may require upgrading", "Upgrade files",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, Icons.QUESTION, new String[]{"Yes", "No"}, "No");
            upgradeFilesButton.setEnabled(true);
            upgradeFilesButton.setVisible(true);
            upgradeFilesButton.setText("Upgrade Experiment Files");
            if (selectedOption == JOptionPane.YES_OPTION) {
                upgradeFilesButton.doClick();
            }
        } else if (upgrader.canUpgradeSegmRun()) {
            upgradeFilesButton.setEnabled(true);
            upgradeFilesButton.setVisible(true);
            upgradeFilesButton.setText("Upgrade SegmRun Files");
        } else {
            upgradeFilesButton.setEnabled(false);
            upgradeFilesButton.setVisible(false);
        }
    }

    public void loadExperimentFromRootFolder(File experimentRoot) {
        try {
            String absoluteExpPath = experimentRoot.getAbsolutePath();
            Optional<File> expJ = getExperimentJsonFile(absoluteExpPath);
            if (!expJ.isPresent() || !expJ.get().exists()) {
                MessageDialogs.showErrorDialog(null, Messages.WRONG_EXPERIMENT_FOLDER);
                return;
            }
            Optional<File> bfF = getExperimentBestFocusFolder(absoluteExpPath);
            if (!bfF.isPresent() || !bfF.get().exists()) {
                MessageDialogs.showErrorDialog(null, Messages.BEST_FOCUS_FOLDER_NOT_PRESENT);
                return;
            }
            try {
                experiment = Experiment.loadFromJSON(expJ.get());
                experiment.setExperimentRootPath(absoluteExpPath);
                experiment.setExperimentRootFolderName(experimentRoot.getName());
                File outputFolder = new File(FilenameUtils.concat(absoluteExpPath, OUTPUT_FOLDER_NAME));
                experiment.setOutputPath(outputFolder.exists() ? outputFolder.getAbsolutePath() : absoluteExpPath);
            } catch (Exception e) {
                MessageDialogs.showErrorDialog(null, Messages.ERROR_DURING_PARSE_EXPERIMENT);
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            }
//            configure(experimentRoot, true);
        } catch (Exception ex) {
            MessageDialogs.showErrorDialog(FileUpgraderFrame.this, Messages.ERROR_DURING_SELECT_EXPERIMENT);
            LOGGER.error(ExceptionUtils.getStackTrace(ex));
        }
    }

    public static Optional<File> getExperimentJsonFile(String absoluteExpPath) {
        File[] files = new File(absoluteExpPath).listFiles(getExperimentJsonFileFilter());
        if (files == null || files.length == 0) {
            files = new File(FilenameUtils.concat(absoluteExpPath, OUTPUT_FOLDER_NAME)).listFiles(getExperimentJsonFileFilter());
        }
        if (files != null && files.length > 0) {
            return Optional.ofNullable(files[0]);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<File> getExperimentBestFocusFolder(String absoluteExpPath) {
        File[] files = new File(absoluteExpPath).listFiles(t -> t.isDirectory() && t.getName().toLowerCase().equals("bestfocus"));
        if (files == null || files.length == 0) {
            files = new File(FilenameUtils.concat(absoluteExpPath, OUTPUT_FOLDER_NAME)).listFiles(t -> t.isDirectory()
                    && t.getName().toLowerCase().equals("bestfocus"));
        }
        if (files != null && files.length > 0) {
            return Optional.ofNullable(files[0]);
        } else {
            return Optional.empty();
        }
    }

    private static FilenameFilter getExperimentJsonFileFilter() {
        return (dir, name) -> "experiment.json".equalsIgnoreCase(name);
    }

    public static void main(String[] args) {
//        Include dependencies with provided scope
//        new ImageJ().setVisible(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        new FileUpgraderFrame();
    }
}