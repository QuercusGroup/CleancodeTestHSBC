/*
 * Unpublished work, Copyright (c) 2021 Shutterfly, Inc., All Rights Reserved
 */

package com.resttest.library.model.entity;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String subtitle;


    @OneToMany(cascade = CascadeType.ALL)
    private Set<Author> authors;

    private String ISBN;

    private Integer quantity;

    private Integer borrowed;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Borrowing> borrowings;

    @Transient
    private Integer available;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

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

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(final Set<Author> authors) {
        this.authors = authors;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(final String ISDN) {
        this.ISBN = ISDN;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(final Integer borrowed) {
        this.borrowed = borrowed;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(final Integer available) {
        this.available = available;
    }

    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(final List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", authors=" + authors +
                ", ISBN='" + ISBN + '\'' +
                ", quantity=" + quantity +
                ", borrowed=" + borrowed +
                ", borrowings=" + borrowings +
                ", available=" + available +
                '}';
    }
}
