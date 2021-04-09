/*
 * Unpublished work, Copyright (c) 2021 Shutterfly, Inc., All Rights Reserved
 */

package com.resttest.library.model.googleapi;

/**
 * Maps an item returned from the Goggle Books API
 */
public class Item {

    private VolumeInfo volumeInfo;

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(final VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
