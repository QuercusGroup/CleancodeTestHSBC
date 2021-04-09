package com.resttest.library.service;

import java.util.List;

import com.resttest.library.model.entity.Book;
import com.resttest.library.model.entity.Borrowing;
import com.resttest.library.model.googleapi.VolumeInfo;
import com.resttest.library.model.request.BorrowingRequest;

public interface BookService {

    List<Book> fetchAllBooks();

    Book addVolumeToLibrary(String ISBN, VolumeInfo volumeInfo, Integer quantity);

    Borrowing saveBorrowing(String ISBN, BorrowingRequest borrowingRequest);

    Borrowing updateBorrowing(String ISBN, Integer borrowingId, BorrowingRequest borrowingRequest);

}
