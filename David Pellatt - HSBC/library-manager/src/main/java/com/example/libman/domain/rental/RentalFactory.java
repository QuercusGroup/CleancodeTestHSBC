/**
 * 
 */
package com.example.libman.domain.rental;

import java.time.LocalDate;

import com.example.libman.domain.book.Book;
import com.example.libman.domain.borrower.Borrower;

/**
 * Factory to create Rental records.
 * 
 * @author David Pellatt
 */
public class RentalFactory {
	/**
	 * Create a Rental for the given Borrower and Book.
	 */
	public static final Rental create(Borrower borrower, Book book) {
		Rental rental = new Rental();
		rental.setBorrowerId(borrower.getKey());
		rental.setBookIsbn(book.getIsbn());
		rental.setStartDate(LocalDate.now());
		return rental;
	}

	/**
	 * Prevent instance creation.
	 */
	private RentalFactory() {
		super();
	}
}
