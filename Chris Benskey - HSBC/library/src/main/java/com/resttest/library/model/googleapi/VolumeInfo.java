/*
 * Unpublished work, Copyright (c) 2021 Shutterfly, Inc., All Rights Reserved
 */

package com.resttest.library.model.googleapi;

import java.util.Collection;
import java.util.List;

/**
 * Model the Volume Info a Book returned from Google Book Volume API
 */
public class VolumeInfo {

    private String title;

    private String subtitle;

    private String publishedDate;

    private List<String> authors;

    private String description;

    private Collection<IndustryIdentifier> industryIdentifiers;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(final String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(final List<String> authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Collection<IndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(final Collection<IndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }
}