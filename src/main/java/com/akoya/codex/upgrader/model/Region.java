package com.akoya.codex.upgrader.model;

/**
 *
 * @author Vishal
 */
public class Region {
    private final int index;
    private final int tilesHigh;
    private final int tilesWide;

    public Region(int index, int tilesHigh, int tilesWide) {
        this.index = index;
        this.tilesHigh = tilesHigh;
        this.tilesWide = tilesWide;
    }

    public int getIndex() {
        return index;
    }

    public int getTilesHigh() {
        return tilesHigh;
    }

    public int getTilesWide() {
        return tilesWide;
    }
}
