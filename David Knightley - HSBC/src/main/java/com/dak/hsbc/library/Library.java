package com.dak.hsbc.library;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.InitBinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class Library {

    private static final String CATALOGUE_FILENAME = "/catalogue.txt";
    private static final int DAYS_TO_RENT = 7;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private final Map<Author,List<Book>> authorCatalogue;
    private final Map<String,Book> bookCatalogue;

    private final Map<String,Date> bookRentals;


    public Library() {
        this.bookCatalogue = new HashMap<>();
        this.authorCatalogue = new HashMap<>();
        this.bookRentals = new HashMap<>();
    }

    public int loadCatalogue() {
        URL url = this.getClass().getResource(CATALOGUE_FILENAME);
        int bookCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(url.getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] sa = line.split(",");
                    int year = Integer.parseInt(sa[3]);
                    Author author = new Author(sa[1]);
                    Book book = new Book(sa[0], sa[2], author, year);
                    addBook(book);
                    bookCount++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading Books");
        }
        return bookCount;
    }

    public void addBook(Book book) {
        bookCatalogue.put(book.getISBN(), book);
        authorCatalogue.computeIfAbsent(book.getAuthor(), k -> new ArrayList<>()).add(book);
    }

    public List<Book> getBooksByAuthor(String lastname) {
        return authorCatalogue.entrySet().stream()
                .filter(e -> e.getKey().getLastName().equals(lastname))
                .map(e -> e.getValue())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<Book> searchByTitle(String keyWord) {
        String searchString = keyWord.toLowerCase();
        return bookCatalogue.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(searchString))
                .collect(Collectors.toList());
    }

    public boolean isAvailable(String isbn) {
        return !bookRentals.containsKey(isbn);
    }

    public String withdrawBook(String isbn) {
        synchronized (bookRentals) {
            if (isAvailable(isbn)) {
                Date dueDate = getDueDate();
                bookRentals.put(isbn, dueDate);
                return sdf.format(dueDate);
            } else {
                return null;
            }
        }
    }

    public boolean returnBook(String isbn) {
        synchronized (bookRentals) {
            Date dueDate = bookRentals.remove(isbn);
            // If the book was never withdrawn, cannot be overdue
            return dueDate == null ? false : isOverDue(dueDate);
        }
    }

    private Date getDueDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, DAYS_TO_RENT);
        // Set to 1 minute before midnight
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        return cal.getTime();
    }

    private boolean isOverDue(Date date) {
        Calendar now = Calendar.getInstance();
        return now.getTime().after(date);
    }
}
