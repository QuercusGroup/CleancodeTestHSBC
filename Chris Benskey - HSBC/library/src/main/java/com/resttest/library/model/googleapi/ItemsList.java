/*
 * Unpublished work, Copyright (c) 2021 Shutterfly, Inc., All Rights Reserved
 */

package com.resttest.library.model.googleapi;

import java.util.List;

/**
 * Wraps the Google Books Volume search result list of items
 */
public class ItemsList {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(final List<Item> items) {
        this.items = items;
    }
}
