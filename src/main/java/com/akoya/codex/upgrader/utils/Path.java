package com.akoya.codex.upgrader.utils;

public class Path {
    private final String experimentSubdirectory;
    private final String sharePath;

    public Path(String experimentSubdirectory, String sharePath) {
        this.experimentSubdirectory = experimentSubdirectory;
        this.sharePath = sharePath;
    }

    public String getExperimentSubdirectory() {
        return experimentSubdirectory;
    }

    public String getSharePath() {
        return sharePath;
    }
}
