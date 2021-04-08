package com.librarymanagementsystem.service;

import com.librarymanagementsystem.model.BorrowBook;
import com.librarymanagementsystem.repository.BorrowBookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BorrowBookServiceTest {

    @InjectMocks
    private BorrowBookService borrowBookService;

    @Mock
    private BorrowBookRepository repository;

    @Mock
    private BookService bookService;

    @Test
    public void testBorrowBook() {
        BorrowBook borrowBook = new BorrowBook();
        Mockito.doNothing().when(bookService).updateStatus(Mockito.anyString(),Mockito.anyString());
        borrowBookService.borrowBook(borrowBook);
        Assert.assertNotNull(borrowBook);
    }

}
