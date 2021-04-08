package com.example.libman.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libman.domain.book.Book;
import com.example.libman.domain.book.BookFactory;
import com.example.libman.domain.book.IsbnFactory;
import com.example.libman.services.LibraryService;

/**
 * Controller providing endpoints to manage Books in the Library.
 * 
 * @author David Pellatt
 */
@RestController
public class LibraryManagerController {
	// Services this controller depends on
	private LibraryService libraryService;

	private static final Logger log = LoggerFactory.getLogger(LibraryManagerController.class);

	/**
	 * @param libraryService
	 */
	public LibraryManagerController(LibraryService libraryService) {
		super();

		this.libraryService = libraryService;
	}

	@GetMapping(value = "/status")
	public String status() {
		return "We're up!";
	}

	@PutMapping(value = "/addBookToLibrary/{isbn}/{title}")
	public void addBookToLibrary(@PathVariable long isbn, @PathVariable String title) {
		Book book = libraryService.addBook(BookFactory.create(IsbnFactory.create(isbn), title));

		log.info("Added Book to Library:{}", book);
	}
}
