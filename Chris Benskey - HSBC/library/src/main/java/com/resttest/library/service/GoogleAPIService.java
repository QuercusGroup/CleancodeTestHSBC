/*
 * Unpublished work, Copyright (c) 2021 Shutterfly, Inc., All Rights Reserved
 */

package com.resttest.library.service;

import com.resttest.library.model.googleapi.VolumeInfo;

/**
 * Behaviors for interacting with Google Book Volume API
 */
public interface GoogleAPIService {

    /**
     * The volume number of the book to search
     * @param ISBN The value to search
     * @return {@link VolumeInfo}
     */
    VolumeInfo searchForBookByISBN(String ISBN);
}
