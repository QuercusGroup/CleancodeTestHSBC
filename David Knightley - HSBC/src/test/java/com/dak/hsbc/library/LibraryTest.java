package com.dak.hsbc.library;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.*;

public class LibraryTest {

    @Test
    public void testLoadAndAddBooks() {
        final int catalogueSize = 12;
        Library lib = new Library();
        int bookCount = lib.loadCatalogue();
        assertEquals(catalogueSize, bookCount);
        Map catalogue = getMapByName(lib, "bookCatalogue");
        assertEquals(catalogueSize, catalogue.size());
    }

    @Test
    public void testGetBooksByAuthor() {
        String firstName_1 = "First N";
        String firstName_2 = "First Diff";
        String lastName_1 = "Lastname";
        String lastName_2 = "AnotherLast";

        String ID1 = "id1";
        String ID2 = "id2";
        String ID3 = "id3";
        String ID4 = "id4";
        String ID5 = "id5";

        Author author1 = new Author(firstName_1, lastName_1);
        Author author2 = new Author(firstName_2, lastName_1);
        Author author3 = new Author(firstName_1, lastName_2);

        Book book1 = new Book(ID1, "Book Title 1", author1, 2000);
        Book book2 = new Book(ID2, "Book Title 2", author1, 2001);
        Book book3 = new Book(ID3, "Book Title 3", author2, 2002);
        Book book4 = new Book(ID4, "Book Title 4", author3, 2003);
        Book book5 = new Book(ID5, "Book Title 5", author1, 2004);

        Library lib = new Library();
        lib.addBook(book1);
        lib.addBook(book2);
        lib.addBook(book3);
        lib.addBook(book4);
        lib.addBook(book5);

        List<Book> bookList = lib.getBooksByAuthor(lastName_1);
        assertEquals(4, bookList.size());
        bookList.forEach(b -> assertTrue(b.getAuthor().getLastName().equals(lastName_1)));
        assertFalse(bookList.contains(book4));

        List<Book> bookList2 = lib.getBooksByAuthor(lastName_2);
        assertEquals(1, bookList2.size());
        assertEquals(book4, bookList2.get(0));

        List<Book> bookList3 = lib.getBooksByAuthor("notAnAuthor");
        assertNotNull(bookList3);
        assertTrue(bookList3.isEmpty());

        assertEquals(3, getMapByName(lib, "authorCatalogue").size());
    }

    @Test
    public void testGetBooksByTitle() {
        Author author = new Author("First Last");

        Book book1 = new Book("ID_1", "Title", author, 2001);
        Book book2 = new Book("ID_2", "Another Title", author, 2001);
        Book book3 = new Book("ID_3", "TitleThree", author, 2001);
        Book book4 = new Book("ID_4", "Not a valid Titl...", author, 2001);
        Book book5 = new Book("ID_5", "Nor this one", author, 2001);
        Book book6 = new Book("ID_6", "Lower case title", author, 2001);

        Library lib = new Library();
        lib.addBook(book1);
        lib.addBook(book2);
        lib.addBook(book3);
        lib.addBook(book4);
        lib.addBook(book5);
        lib.addBook(book6);

        List<Book> bookList = lib.searchByTitle("Title");
        assertEquals(4, bookList.size());
        // Prove they are unique
        Set<Book> dedup = new HashSet<>(bookList);
        assertEquals(4, dedup.size());

        assertTrue(bookList.stream().noneMatch(b -> b.getISBN().equals("ID_4") || b.getISBN().equals("ID_5")));
    }

    @Test
    public void testWithdrawAndReturn() {
        final String ID_1 = "ID_1";
        final String ID_2 = "ID_2";

        Author author = new Author("First Last");

        Book book1 = new Book(ID_1, "Title", author, 2001);
        Book book2 = new Book(ID_2, "Another Title", author, 2001);

        Library lib = new Library();
        lib.addBook(book1);
        lib.addBook(book2);

        assertTrue(lib.isAvailable(ID_1));
        assertTrue(lib.isAvailable(ID_2));

        lib.withdrawBook(ID_1);
        assertFalse(lib.isAvailable(ID_1));
        assertTrue(lib.isAvailable(ID_2));

        assertFalse(lib.returnBook(ID_1));
        assertTrue(lib.isAvailable(ID_1));
        assertTrue(lib.isAvailable(ID_2));
    }

    @Test
    public void testOverdueBook() {
        final String ID_1 = "ID_1";
        final String ID_2 = "ID_2";

        Library lib = new Library();

        Map<String,Date> withdrawlMap = getMapByName(lib, "bookRentals");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        withdrawlMap.put(ID_1, cal.getTime());

        assertFalse(lib.isAvailable(ID_1));
        assertTrue(lib.isAvailable(ID_2));

        lib.withdrawBook(ID_2);
        assertFalse(lib.isAvailable(ID_1));
        assertFalse(lib.isAvailable(ID_2));

        assertTrue(lib.returnBook(ID_1));
        assertFalse(lib.returnBook(ID_2));
    }



    private Map getMapByName(Library library, String name) {
        try {
            Field f = library.getClass().getDeclaredField(name);
            f.setAccessible(true);
            return (Map) f.get(library);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
