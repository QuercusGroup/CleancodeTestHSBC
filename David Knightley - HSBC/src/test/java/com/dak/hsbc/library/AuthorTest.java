package com.dak.hsbc.library;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuthorTest {

    @Test
    public void testConstructionSeparateNames() {
        String first = "First Name";
        String last = "Last Name";
        Author author = new Author(first, last);

        assertEquals(first, author.getFirstNames());
        assertEquals(last, author.getLastName());
    }

    @Test
    public void testConstructionSingleNameString() {
        String first = "First Names";
        String last = "Last-Name";
        String combined = new StringBuilder(first).append(" ").append(last).toString();

        Author author = new Author(combined);

        assertEquals(first, author.getFirstNames());
        assertEquals(last, author.getLastName());
        assertEquals(combined, author.getAuthor());
    }
}
