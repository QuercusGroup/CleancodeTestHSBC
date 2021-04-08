package com.librarymanagementsystem.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "Book", description = "Operations about Books")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/book/{isbn}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get a book using its ISBN")
	public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
		Book result = bookService.getBook(isbn);
		HttpStatus httpStatus;
		if (result != null) {
			httpStatus = HttpStatus.OK;
		} else {
			httpStatus = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<Book>(result, httpStatus);

	}

	
	@RequestMapping(value = "/book", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a book")
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
		bookService.addBook(book);
		return new ResponseEntity<>(book, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	
	@ApiOperation(value = "List all books")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/book", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a book")
	public ResponseEntity<Book> updateBook(@RequestBody Book book) {
		HttpStatus httpStatus;
		if (bookService.isBookAvailable(book.getIsbn())) {
			bookService.addBook(book);
			httpStatus = HttpStatus.OK;
		} else {
			httpStatus = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<>(book, httpStatus);
	}

	
	@RequestMapping(value = "/book/{isbn}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a book")
	public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
		bookService.removeBook(isbn);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	@RequestMapping(value = "/borrowed-books", method = RequestMethod.GET, consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Borrowed books list")
	public ResponseEntity<List<Book>> borrowedBooks() {
		List<Book> availableBooks = bookService.getBookByStatus("Borrow");
		return new ResponseEntity<>(availableBooks, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/available-books", method = RequestMethod.GET, consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Available books list")
	public ResponseEntity<List<Book>> availableBooks() {
		List<Book> availableBooks = bookService.getBookByStatus("Available");
		return new ResponseEntity<>(availableBooks, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "search/book/{name}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Books list by author name")
	public ResponseEntity<List<Book>> getBooksByTitleAuthor(@PathVariable String name) {
		Book booksearch = new Book();
		List<Book> search = new ArrayList<Book>();
		HttpStatus httpStatus;

		List<Book> books = bookService.getAllBooks();

		books.forEach(book -> {
			if (book.getTitle().equals(name) || book.getAuthor().equals(name)) {

				booksearch.setTitle(book.getTitle());
				booksearch.setAuthor(book.getAuthor());
				booksearch.setPublisher(book.getPublisher());
				booksearch.setIsbn(book.getIsbn());
				booksearch.setStatus(book.getStatus());
				booksearch.setIsbn(book.getIsbn());
				search.add(book);
			}

		});

		if (search.size() > 0) {
			httpStatus = HttpStatus.OK;
		} else {
			httpStatus = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<List<Book>>(search, httpStatus);
	}

}
