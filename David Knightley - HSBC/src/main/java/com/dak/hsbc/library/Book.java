package com.dak.hsbc.library;

import java.util.Date;

public class Book {

    private final String isbn;
    private final String title;
    private final Author author;
    private final int year;

    public Book(String id, String title, Author author, int year) {
        this.isbn = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getISBN() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

}
