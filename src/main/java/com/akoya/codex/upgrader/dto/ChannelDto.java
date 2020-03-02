package com.akoya.codex.upgrader.dto;

public class ChannelDto {
    private String dyeName;
    private Double exposureTime;
    private Integer index;
    private String markerClass;
    private String markerDisplayName;
    private String markerName;
    private String name;

    public String getDyeName() {
        return dyeName;
    }

    public void setDyeName(String dyeName) {
        this.dyeName = dyeName;
    }

    public Double getExposureTime() {
        return exposureTime;
    }

    public void setExposureTime(Double exposureTime) {
        this.exposureTime = exposureTime;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getMarkerClass() {
        return markerClass;
    }

    public void setMarkerClass(String markerClass) {
        this.markerClass = markerClass;
    }

    public String getMarkerDisplayName() {
        return markerDisplayName;
    }

    public void setMarkerDisplayName(String markerDisplayName) {
        this.markerDisplayName = markerDisplayName;
    }

    public String getMarkerName() {
        return markerName;
    }

    public void setMarkerName(String markerName) {
        this.markerName = markerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
