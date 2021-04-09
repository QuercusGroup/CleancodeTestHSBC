/*
 * Unpublished work, Copyright (c) 2021 Shutterfly, Inc., All Rights Reserved
 */

package com.resttest.library.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.resttest.library.model.entity.Book;
import com.resttest.library.model.entity.Borrowing;
import com.resttest.library.model.googleapi.VolumeInfo;
import com.resttest.library.model.request.BorrowingRequest;
import com.resttest.library.model.request.StoreBookRequest;
import com.resttest.library.service.BookService;
import com.resttest.library.service.GoogleAPIService;

@RestController
public class LibraryRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryRestController.class);

    private final GoogleAPIService googleAPIService;

    private final BookService bookService;

    public LibraryRestController(final GoogleAPIService googleAPIService, final BookService bookService) {
        this.googleAPIService = googleAPIService;
        this.bookService = bookService;
    }

    @GetMapping(value = "/books", produces = "application/json")
    public ResponseEntity<List<Book>> allBooks() {
        LOGGER.info("Getting all books");
        final List<Book> books = bookService.fetchAllBooks();
        return ResponseEntity.ok(books);

    }

    @PostMapping(value = "/book", produces = "application/json")
    public ResponseEntity<Book> storeBooks(@RequestBody final StoreBookRequest storeBookRequest) {
        LOGGER.info("Posting storeBookRequest: {}", storeBookRequest);

        final VolumeInfo volumeInfo = googleAPIService.searchForBookByISBN(storeBookRequest.getISBN());
        if (Objects.isNull(volumeInfo)) {
            LOGGER.error("Volume Info not found: {}", storeBookRequest);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (storeBookRequest.getQuantity() < 0) {
            LOGGER.error("Negative quantity supplied to storedBookRequest: {}", storeBookRequest);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        final Book book = bookService.addVolumeToLibrary(storeBookRequest.getISBN(),
                volumeInfo, storeBookRequest.getQuantity());
        return ResponseEntity.ok(book);

    }


    @PostMapping(value = "/book/{ISBN}/borrowing", produces = "application/json")
    public ResponseEntity<Borrowing> storeBorrowing(@PathVariable("ISBN") final String ISBN,
                                                    @RequestBody final BorrowingRequest borrowingRequest) {
        LOGGER.info("Posting borrowingRequest: {} {}", ISBN, borrowingRequest);
        final Borrowing borrowing = bookService.saveBorrowing(ISBN, borrowingRequest);
        return ResponseEntity.ok(borrowing);

    }

    @PutMapping(value = "/book/{ISBN}/borrowing/{borrowingId}", produces = "application/json")
    public ResponseEntity<Borrowing> updateBorrowing(@PathVariable("ISBN") final String ISBN,
                                                     @PathVariable("borrowingId") final Integer borrowingId,
                                                     @RequestBody final BorrowingRequest borrowingRequest) {
        LOGGER.info("Putting borrowingRequest: {} {} {}", ISBN, borrowingId, borrowingRequest);
        final Borrowing borrowing = bookService.updateBorrowing(ISBN, borrowingId, borrowingRequest);
        return ResponseEntity.ok(borrowing);

    }
}
