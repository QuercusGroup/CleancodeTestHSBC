/**
 * 
 */
package com.example.libman.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.libman.domain.book.Book;
import com.example.libman.domain.book.Isbn;
import com.example.libman.domain.borrower.Borrower;
import com.example.libman.domain.rental.Rental;
import com.example.libman.domain.rental.RentalFactory;
import com.example.libman.repositories.BookRepository;
import com.example.libman.repositories.BorrowerRepository;
import com.example.libman.repositories.RentalRepository;

/**
 * Service to encapsulate any rules around lending books.
 * 
 * @author David Pellatt
 */
@Service
public class BookRentalService {
	private BookRepository bookRepository;
	private BorrowerRepository borrowerRepository;
	private RentalRepository rentalRepository;

	private static final Logger log = LoggerFactory.getLogger(BookRentalService.class);

	public BookRentalService(BookRepository bookRepository, BorrowerRepository borrowerRepository,
			RentalRepository rentalRepository) {
		super();

		this.bookRepository = bookRepository;
		this.borrowerRepository = borrowerRepository;
		this.rentalRepository = rentalRepository;
	}

	/**
	 * Record a Rental of a Book to a Borrower.
	 */
	public Optional<Rental> loanBook(Isbn isbn, long borrowerId) {
		Optional<Book> maybeBook = bookRepository.findById(isbn);

		if (maybeBook.isEmpty()) {
			log.warn("Failed to find Book with ISBN:{}", isbn);
			return Optional.empty();
		}

		Optional<Borrower> maybeBorrower = borrowerRepository.findById(borrowerId);

		if (maybeBorrower.isEmpty()) {
			log.warn("Failed to find Borrower with id:{}", borrowerId);
			return Optional.empty();
		}

		Borrower borrower = maybeBorrower.get();
		Book book = maybeBook.get();

		Rental rental = RentalFactory.create(borrower, book);

		return Optional.of(rentalRepository.save(rental));
	}

	/**
	 * Get the current Rentals for the given Borrower.
	 */
	public List<Rental> findRentals(Long borrowerId) {
		return rentalRepository.findByBorrowerId(borrowerId);
	}

	/**
	 * Get all current Rentals, for all Borrowers.
	 */
	public Iterable<Rental> findAllRentals() {
		return rentalRepository.findAll();
	}

}
