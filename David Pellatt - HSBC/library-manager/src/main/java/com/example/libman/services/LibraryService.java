/**
 * 
 */
package com.example.libman.services;

import org.springframework.stereotype.Service;

import com.example.libman.domain.book.Book;
import com.example.libman.repositories.BookRepository;

/**
 * Service to manage the books within a library.
 * 
 * @author David Pellatt
 */
@Service
public class LibraryService {
	private BookRepository bookRepository;

	public LibraryService(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	/**
	 * @param book
	 * @return the persisted state
	 */
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

}
