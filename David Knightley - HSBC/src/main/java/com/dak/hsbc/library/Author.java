package com.dak.hsbc.library;

import java.util.Objects;

public class Author {

    private String firstNames;
    private String lastName;

    public Author(String author) {
        int index = author.lastIndexOf(" ");
        if (index < 0) {
            firstNames = "";
            lastName = author;
        } else {
            firstNames = author.substring(0, index);
            lastName = author.substring(index + 1);
        }
    }

    public Author(String first, String last) {
        this.firstNames = first;
        this.lastName = last;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAuthor() {
        return firstNames + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(firstNames, author.firstNames) &&
                Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNames, lastName);
    }
}
