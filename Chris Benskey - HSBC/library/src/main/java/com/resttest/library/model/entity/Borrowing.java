/*
 * Unpublished work, Copyright (c) 2021 Shutterfly, Inc., All Rights Reserved
 */

package com.resttest.library.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Borrowing {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Book book;

    private BorrowingStatus status;

    private String borrowerName;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public BorrowingStatus getStatus() {
        return status;
    }

    public void setStatus(final BorrowingStatus status) {
        this.status = status;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(final String borrowerName) {
        this.borrowerName = borrowerName;
    }

    @Override
    public String toString() {
        return "Borrowing{" +
                "id=" + id +
                ", book=" + book +
                ", status=" + status +
                ", borrowerName='" + borrowerName + '\'' +
                '}';
    }
}
