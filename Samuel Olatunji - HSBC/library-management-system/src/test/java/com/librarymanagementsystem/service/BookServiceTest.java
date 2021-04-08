package com.librarymanagementsystem.service;

import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository repository;

    @Test
    public void testGetBookStatus() {
        Book book = new Book();
        book.setStatus("borrow");
        Mockito.when(repository.findOne(Mockito.anyString())).thenReturn(book);
        Assert.assertEquals("borrow", bookService.getBookStatus("123"));
    }

    @Test
    public void testUpdateStatus() {
        Book book = new Book();
        book.setStatus("borrow");
        Mockito.when(repository.findOne(Mockito.anyString())).thenReturn(book);
        bookService.updateStatus("123","available");
        Assert.assertEquals("available", book.getStatus());
    }
}
