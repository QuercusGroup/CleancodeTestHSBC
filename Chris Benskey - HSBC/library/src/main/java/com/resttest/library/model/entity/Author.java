/*
 * Unpublished work, Copyright (c) 2021 Shutterfly, Inc., All Rights Reserved
 */

package com.resttest.library.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
