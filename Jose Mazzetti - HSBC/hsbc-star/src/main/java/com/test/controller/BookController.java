package com.test.controller;

import com.test.dto.Book;
import com.test.exception.BookException;
import com.test.service.BookService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
  Controller that handles book CRUD.
 */

@RestController
@AllArgsConstructor
public class BookController {

    public final BookService bookService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success. Get all books"),
            @ApiResponse(responseCode = "500", description = "Server error"),
            @ApiResponse(responseCode = "403", description = "Forbiden")
    })
    @GetMapping(path = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Book> getBook() {
        return new ResponseEntity(bookService.getBooks(), HttpStatus.OK);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Saved book."),
        @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping(path = "/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Book> addBook(@RequestBody Book book) {
        book.setUuid(UUID.randomUUID());
        bookService.saveBook(book);
        return new ResponseEntity(book, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book deleted."),
        @ApiResponse(responseCode = "406", description = "Book not Accepted.")
    })
    @DeleteMapping("/book/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable UUID id) {
        try{
            bookService.deleteBook(id);
        }catch (BookException e){
            return new ResponseEntity(id, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(id, HttpStatus.ACCEPTED);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> putBook(@PathVariable UUID id) {

        return new ResponseEntity(new Book(), HttpStatus.CREATED);
    }

    @PatchMapping("/book/{id}")
    public @ResponseBody ResponseEntity<Book> patchBook() {
        return new ResponseEntity<Book>(new Book(), HttpStatus.OK);
    }

}
