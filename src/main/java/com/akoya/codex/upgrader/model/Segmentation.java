package com.akoya.codex.upgrader.model;

/**
 *
 * @author Vishal
 */
public class Segmentation {
    private boolean use_membrane;
    private double maxCutoff;
    private double minCutoff;
    private double relativeCutoff;
    private int nuclearStainChannel;
    private int nuclearStainCycle;
    private int membraneStainChannel;
    private int membraneStainCycle;
    private int radius;
    private boolean count_puncta;
    private double inner_ring_size;
    private double sizeCutoffFactor;
    private int[] readoutChannels;
    private boolean subtractInnerRing;
    private boolean showImage;
    private boolean dont_inverse_memb;
    private boolean delaunay_graph;
    private int concentricCircles;
    private boolean anisotropic_reg_growth;
    private boolean single_plane_quant;

    public boolean isUse_membrane() {
        return use_membrane;
    }

    public void setUse_membrane(boolean use_membrane) {
        this.use_membrane = use_membrane;
    }

    public double getMaxCutoff() {
        return maxCutoff;
    }

    public void setMaxCutoff(double maxCutoff) {
        this.maxCutoff = maxCutoff;
    }

    public double getMinCutoff() {
        return minCutoff;
    }

    public void setMinCutoff(double minCutoff) {
        this.minCutoff = minCutoff;
    }

    public double getRelativeCutoff() {
        return relativeCutoff;
    }

    public void setRelativeCutoff(double relativeCutoff) {
        this.relativeCutoff = relativeCutoff;
    }

    public int getNuclearStainChannel() {
        return nuclearStainChannel;
    }

    public void setNuclearStainChannel(int nuclearStainChannel) {
        this.nuclearStainChannel = nuclearStainChannel;
    }

    public int getNuclearStainCycle() {
        return nuclearStainCycle;
    }

    public void setNuclearStainCycle(int nuclearStainCycle) {
        this.nuclearStainCycle = nuclearStainCycle;
    }

    public int getMembraneStainChannel() {
        return membraneStainChannel;
    }

    public void setMembraneStainChannel(int membraneStainChannel) {
        this.membraneStainChannel = membraneStainChannel;
    }

    public int getMembraneStainCycle() {
        return membraneStainCycle;
    }

    public void setMembraneStainCycle(int membraneStainCycle) {
        this.membraneStainCycle = membraneStainCycle;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isCount_puncta() {
        return count_puncta;
    }

    public void setCount_puncta(boolean count_puncta) {
        this.count_puncta = count_puncta;
    }

    public double getInner_ring_size() {
        return inner_ring_size;
    }

    public void setInner_ring_size(double inner_ring_size) {
        this.inner_ring_size = inner_ring_size;
    }

    public double getSizeCutoffFactor() {
        return sizeCutoffFactor;
    }

    public void setSizeCutoffFactor(double sizeCutoffFactor) {
        this.sizeCutoffFactor = sizeCutoffFactor;
    }

    public int[] getReadoutChannels() {
        return readoutChannels;
    }

    public void setReadoutChannels(int[] readoutChannels) {
        this.readoutChannels = readoutChannels;
    }

    public boolean isSubtractInnerRing() {
        return subtractInnerRing;
    }

    public void setSubtractInnerRing(boolean subtractInnerRing) {
        this.subtractInnerRing = subtractInnerRing;
    }

    public boolean isShowImage() {
        return showImage;
    }

    public void setShowImage(boolean showImage) {
        this.showImage = showImage;
    }

    public boolean isDont_inverse_memb() {
        return dont_inverse_memb;
    }

    public void setDont_inverse_memb(boolean dont_inverse_memb) {
        this.dont_inverse_memb = dont_inverse_memb;
    }

    public boolean isDelaunay_graph() {
        return delaunay_graph;
    }

    public void setDelaunay_graph(boolean delaunay_graph) {
        this.delaunay_graph = delaunay_graph;
    }

    public int getConcentricCircles() {
        return concentricCircles;
    }

    public void setConcentricCircles(int concentricCircles) {
        this.concentricCircles = concentricCircles;
    }

    public boolean isAnisotropic_reg_growth() {
        return anisotropic_reg_growth;
    }

    public void setAnisotropic_reg_growth(boolean anisotropic_reg_growth) {
        this.anisotropic_reg_growth = anisotropic_reg_growth;
    }

    public boolean isSingle_plane_quant() {
        return single_plane_quant;
    }

    public void setSingle_plane_quant(boolean single_plane_quant) {
        this.single_plane_quant = single_plane_quant;
    }
}
