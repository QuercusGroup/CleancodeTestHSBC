/*
 * Unpublished work, Copyright (c) 2021 Shutterfly, Inc., All Rights Reserved
 */

package com.resttest.library.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import com.resttest.library.model.googleapi.VolumeInfo;

class GoogleAPIServiceIntegrationTest {

    GoogleAPIServiceImpl googleAPIService;

    @BeforeEach
    void setUp() {
        googleAPIService = new GoogleAPIServiceImpl(new RestTemplate(),
                "https://www.googleapis.com/books/v1/volumes?q=isbn:%s");
    }

    @Test
    void searchForBookByISBNSuccess() {
        final VolumeInfo volumeInfo = googleAPIService.searchForBookByISBN("9781735113012");
        Assert.assertEquals("The Little Book of Friendship", volumeInfo.getTitle());
        Assert.assertEquals("The Best Way to Make a Friend, Is to Be a Friend", volumeInfo.getSubtitle());
        Assert.assertEquals(2, volumeInfo.getAuthors().size());
        Assert.assertEquals("2020-07-10", volumeInfo.getPublishedDate());
    }

    @Test
    void searchForBookByISBNNotFound() {
        final VolumeInfo volumeInfo = googleAPIService.searchForBookByISBN("DOESNOTEXIST");
        Assert.assertNull(volumeInfo);
    }
}