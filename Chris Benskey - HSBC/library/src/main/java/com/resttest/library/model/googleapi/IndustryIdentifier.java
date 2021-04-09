/*
 * Unpublished work, Copyright (c) 2021 Shutterfly, Inc., All Rights Reserved
 */

package com.resttest.library.model.googleapi;

/**
 * Maps industry id returned from Google Books API
 */
public class IndustryIdentifier {

    private String type;

    private String identifier;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }
}
