package com.akoya.codex.upgrader.dto;

import java.util.List;

public class CycleDto {
    private List<ChannelDto> channels;
    private String endTime;
    private Integer index;
    private String startTime;

    public List<ChannelDto> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelDto> channels) {
        this.channels = channels;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
