/*
 * Unpublished work, Copyright (c) 2021 Shutterfly, Inc., All Rights Reserved
 */

package com.resttest.library.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.resttest.library.model.googleapi.Item;
import com.resttest.library.model.googleapi.ItemsList;
import com.resttest.library.model.googleapi.VolumeInfo;

@Service
public class GoogleAPIServiceImpl implements GoogleAPIService {

    private final RestTemplate restTemplate;

    private final String googleAPISearchUrlFormat;

    public GoogleAPIServiceImpl(final RestTemplate restTemplate,
                                @Value("${com.restest.library.service.googleAPISearchUrlFormat}") final String googleAPISearchUrlFormat) {
        this.restTemplate = restTemplate;
        this.googleAPISearchUrlFormat = googleAPISearchUrlFormat;
    }

    @Override
    public VolumeInfo searchForBookByISBN(final String ISBN) {
        if (Objects.isNull(ISBN)) {
            return null;
        }
        final String searchUrl = String.format(googleAPISearchUrlFormat, ISBN.trim().replace("-", ""));
        final ItemsList itemsList = restTemplate.getForObject(searchUrl, ItemsList.class);

        // non-functional defensive style
        if (Objects.nonNull(itemsList) && (Objects.nonNull(itemsList.getItems()))){
            return itemsList.getItems().stream().findAny().orElse(new Item()).getVolumeInfo();
        }

        return null;
    }
}
