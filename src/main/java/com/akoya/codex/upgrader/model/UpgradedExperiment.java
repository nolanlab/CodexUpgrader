package com.akoya.codex.upgrader.model;

import com.akoya.codex.upgrader.JsonSerializable;
import com.akoya.codex.upgrader.MicroscopeTypeEnum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 *
 * @author Vishal
 */
public class UpgradedExperiment implements JsonSerializable {
    private String version;
    private String experimentName;
    private String projectName;
    private String date; // Only for user information
    private String dateProcessed;
    private String path;
    private String outputPath;
    private String immersion;
    private String deconvolution;
    private int magnification; // Only for user information
    private double aperture;
    private MicroscopeTypeEnum microscopeModel;
    private double resolution;
    private double zPitch;
    private int[] wavelengths;

    private int bitness;
    private int numRegions;
    private int numTiles;
    private int numCycles;
    private int numPlanes;
    private int numChannels;
    private int regionWidth;
    private int regionHeight;
    private int frameWidth;
    private int frameHeight;
    private double overlapX;
    private double overlapY;

    private String tilingMode;
    private String backgroundSubtractionMode;
    private int driftCompReferenceCycle;
    private int driftCompReferenceChannel;
    private int bestFocusReferenceCycle;
    private int bestFocusReferenceChannel;
    private int focusingOffset;
    private boolean useBackgroundSubtraction;
    private boolean useDeconvolution;
    private boolean useExtendedDepthOfField;
    private boolean useShadingCorrection;
    private boolean use3dDriftCompensation;
    private boolean useBleachMinimizingCrop;
    private boolean useBlindDeconvolution;
    private boolean useDiagnosticMode;
    private boolean multipointMode;
    private boolean HandEstain;

    public UpgradedExperiment(Experiment exp) {
        this.experimentName = exp.getName();
        this.projectName = exp.getProjName();
        this.date = exp.getDate();
        this.microscopeModel = exp.getMicroscope();
        this.aperture = exp.getNumerical_aperture();
        this.magnification = exp.getMagnification();
        this.immersion = exp.getObjectiveType();
        this.deconvolution = exp.getDeconvolution();
        this.resolution = exp.getPer_pixel_XY_resolution();
        this.zPitch = exp.getZ_pitch();
        this.wavelengths = exp.getEmission_wavelengths();
        this.numRegions = exp.getRegions().size();
        this.regionWidth = exp.getRegionWidthInTiles();
        this.regionHeight = exp.getRegionHeightInTiles();
        this.numTiles = this.regionWidth * this.regionHeight;
        this.numCycles = exp.getNum_cycles();
        this.numChannels = exp.getChannel_names().length;
        this.numPlanes = exp.getNumPlanes();
        this.frameWidth = exp.getTile_width();
        this.frameHeight = exp.getTile_height();
        this.overlapX = exp.getTile_overlap_X();
        this.overlapY = exp.getTile_overlap_Y();
        this.tilingMode = exp.getTiling_mode();
        this.driftCompReferenceChannel = exp.getDrift_comp_channel();
        this.driftCompReferenceCycle = exp.getDriftCompReferenceCycle();
        this.bestFocusReferenceChannel = exp.getBest_focus_channel();
        this.bestFocusReferenceCycle = exp.getBestFocusReferenceCycle();
        this.focusingOffset = exp.getFocusing_offset();
        this.useBackgroundSubtraction = exp.isBgSub();
        this.useBleachMinimizingCrop = exp.isUseBleachMinimizingCrop();
    }


    // -------------------------------------------------------------------------------------------------------------- //
    // Getters & setters

    public String getVersion() {
        return version;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(String dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getImmersion() {
        return immersion;
    }

    public void setImmersion(String immersion) {
        this.immersion = immersion;
    }

    public int getMagnification() {
        return magnification;
    } // Only for user information

    public void setMagnification(int magnification) {
        this.magnification = magnification;
    }

    public double getAperture() {
        return aperture;
    }

    public void setAperture(double aperture) {
        this.aperture = aperture;
    }

    public MicroscopeTypeEnum getMicroscopeModel() {
        return microscopeModel;
    }

    public void setMicroscopeModel(MicroscopeTypeEnum microscope) {
        this.microscopeModel = microscope;
    }

    public double getResolution() {
        return resolution;
    }

    public void setResolution(double resolution) {
        this.resolution = resolution;
    }

    public double getzPitch() {
        return zPitch;
    }

    public void setzPitch(double zPitch) {
        this.zPitch = zPitch;
    }

    public int[] getWavelengths() {
        return wavelengths;
    }

    public void setWavelengths(int[] wavelengths) {
        this.wavelengths = wavelengths;
    }

    public int getBitness() {
        return bitness;
    }

    public void setBitness(int bitness) {
        this.bitness = bitness;
    }

    public int getNumRegions() {
        return numRegions;
    }

    public void setNumRegions(int numRegions) {
        this.numRegions = numRegions;
    }

    public int getNumCycles() {
        return numCycles;
    }

    public void setNumCycles(int numCycles) {
        this.numCycles = numCycles;
    }

    public int getNumPlanes() {
        return numPlanes;
    }

    public void setNumPlanes(int numZPlanes) {
        this.numPlanes = numZPlanes;
    }

    public int getNumChannels() {
        return numChannels;
    }

    public void setNumChannels(int numChannels) {
        this.numChannels = numChannels;
    }

    public int getRegionWidth() {
        return regionWidth;
    }

    public void setRegionWidth(int regionWidth) {
        this.regionWidth = regionWidth;
    }

    public int getRegionHeight() {
        return regionHeight;
    }

    public void setRegionHeight(int regionHeight) {
        this.regionHeight = regionHeight;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int tileWidth) {
        this.frameWidth = tileWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int tileHeight) {
        this.frameHeight = tileHeight;
    }

    public double getOverlapX() {
        return overlapX;
    }

    public void setOverlapX(double tileOverlapX) {
        this.overlapX = tileOverlapX;
    }

    public double getOverlapY() {
        return overlapY;
    }

    public void setOverlapY(double tileOverlapY) {
        this.overlapY = tileOverlapY;
    }

    public String getTilingMode() {
        return tilingMode;
    }

    public void setTilingMode(String tilingMode) {
        this.tilingMode = tilingMode;
    }

    public String getBackgroundSubtractionMode() {
        return backgroundSubtractionMode;
    }

    private void setBackgroundSubtractionMode(String backgroundSubtractionMode) {
        this.backgroundSubtractionMode = backgroundSubtractionMode;
    }

    public String getDeconvolution() {
        return deconvolution;
    }

    public void setDeconvolution(String deconvolution) {
        this.deconvolution = deconvolution;
    }

    public int getDriftCompReferenceCycle() {
        return driftCompReferenceCycle;
    }

    public void setDriftCompReferenceCycle(int driftCompReferenceCycle) {
        this.driftCompReferenceCycle = driftCompReferenceCycle;
    }

    public int getDriftCompReferenceChannel() {
        return driftCompReferenceChannel;
    }

    public void setDriftCompReferenceChannel(int driftCompReferenceChannel) {
        this.driftCompReferenceChannel = driftCompReferenceChannel;
    }

    public int getBestFocusReferenceCycle() {
        return bestFocusReferenceCycle;
    }

    public void setBestFocusReferenceCycle(int bestFocusReferenceCycle) {
        this.bestFocusReferenceCycle = bestFocusReferenceCycle;
    }

    public int getBestFocusReferenceChannel() {
        return bestFocusReferenceChannel;
    }

    public void setBestFocusReferenceChannel(int bestFocusReferenceChannel) {
        this.bestFocusReferenceChannel = bestFocusReferenceChannel;
    }

    public int getFocusingOffset() {
        return focusingOffset;
    }

    public void setFocusingOffset(int focusingOffsetIn) {
        focusingOffset = focusingOffsetIn;
    }

    public boolean isUseBackgroundSubtraction() {
        return useBackgroundSubtraction;
    }

    public void setUseBackgroundSubtraction(boolean useBackgroundSubtraction) {
        this.useBackgroundSubtraction = useBackgroundSubtraction;
        this.backgroundSubtractionMode = "auto";
    }

    public boolean isUseDeconvolution() {
        return useDeconvolution;
    }

    public void setUseDeconvolution(boolean useDeconvolution) {
        this.useDeconvolution = useDeconvolution;
    }

    public boolean isUseExtendedDepthOfField() {
        return useExtendedDepthOfField;
    }

    public void setUseExtendedDepthOfField(boolean useExtendedDepthOfField) {
        this.useExtendedDepthOfField = useExtendedDepthOfField;
    }

    public boolean isUseShadingCorrection() {
        return useShadingCorrection;
    }

    public void setUseShadingCorrection(boolean useShadingCorrection) {
        this.useShadingCorrection = useShadingCorrection;
    }

    public boolean isUse3dDriftCompensation() {
        return use3dDriftCompensation;
    }

    public void setUse3dDriftCompensation(boolean use3dDriftCompensation) {
        this.use3dDriftCompensation = use3dDriftCompensation;
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

    public boolean isUseDiagnosticMode() {
        return useDiagnosticMode;
    }

    public void setUseDiagnosticMode(boolean useDiagnosticMode) {
        this.useDiagnosticMode = useDiagnosticMode;
    }

    boolean isMultipointMode() {
        return multipointMode;
    }

    public void setMultipointMode(boolean multipointMode) {
        this.multipointMode = multipointMode;
    }

    public boolean isHandEStain() {
        return HandEstain;
    }

    public void setHandEstain(boolean handEstain) {
        HandEstain = handEstain;
    }


    // -------------------------------------------------------------------------------------------------------------- //
    // Utility functions


    public long getImageSize() {
        return (bitness / 8) * frameWidth * frameHeight;
    }

    public float getEstimatedSize() {
        return (float) ((getImageSize() * numRegions * numCycles * numTiles * numPlanes * numChannels) * 1.0 / (1024 * 1024 * 1024));
    }

    public String getTileName(int regionIndex, int tileIndex) {
        return String.format("reg%03d_X%02d_Y%02d", regionIndex + 1, getXIndex(tileIndex) + 1, getYIndex(tileIndex) + 1);
    }

    public int getTileIndex(int x, int y) {
        int tileIndex;
        switch (tilingMode.toLowerCase()) {
            case "snakerows":
                tileIndex = regionWidth * y;
                if ((y + 1) % 2 == 1) {
                    tileIndex += x;
                } else {
                    tileIndex += regionWidth - (x + 1);
                }
                return tileIndex;
            case "snakecols":
                tileIndex = regionHeight * x;
                if ((x + 1) % 2 == 1) {
                    tileIndex += y;
                } else {
                    tileIndex += regionHeight - (y + 1);
                }
                return tileIndex;
            case "gridrows":
                return regionWidth * y + (x + 1);
            case "gridcols":
                return regionHeight * x + (y + 1);
            default:
                throw new IllegalArgumentException("Unsupported tiling mode: " + tilingMode);
        }
    }

    public int getXIndex(int tileIndex) {
        switch (tilingMode.toLowerCase()) {
            case "snakerows":
                int x = tileIndex % regionWidth;
                int y = tileIndex / regionWidth;
                if (y % 2 == 1) {
                    x = regionWidth - x;
                    x--;
                }
                return x;
            case "snakecols":
                return (tileIndex / regionHeight);
            case "gridrows":
                return (tileIndex % regionWidth);
            case "gridcols":
                return (tileIndex / regionHeight);
            default:
                throw new IllegalArgumentException("Unsupported tiling mode: " + tilingMode);
        }
    }

    public int getYIndex(int tileIndex) {
        switch (tilingMode.toLowerCase()) {
            case "snakerows":
                return (tileIndex / regionWidth);
            case "snakecols":
                int x = tileIndex / regionHeight;
                int y = tileIndex % regionHeight;
                if (x % 2 == 1) {
                    y = regionHeight - y;
                    y--;
                }
                return y;
            case "gridrows":
                return (tileIndex / regionWidth);
            case "gridcols":
                return (tileIndex % regionHeight);
            default:
                throw new IllegalArgumentException("Unsupported tiling mode: " + tilingMode);
        }
    }

    public int getCroppedTileWidth() {
        return (int) Math.ceil(frameWidth * (1 - overlapX));
    }

    public int getCroppedTileHeight() {
        return (int) Math.ceil(frameHeight * (1 - overlapY));
    }

    public int getOverlapIntX() {
        return (int) (frameWidth * overlapX);
    }

    public int getOverlapIntY() {
        return (int) (frameHeight * overlapY);
    }

    @Override
    public String toJSON() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT) // include static
                .create();
        return gson.toJson(this).replaceAll(",", ",\n");
    }
}
