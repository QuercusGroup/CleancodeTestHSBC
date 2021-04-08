package com.example.libman.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libman.domain.book.IsbnFactory;
import com.example.libman.domain.rental.Rental;
import com.example.libman.dto.RentalListWrapper;
import com.example.libman.services.BookRentalService;

/**
 * Controller providing endpoints to manage Book Rentals.
 * 
 * @author David Pellatt
 */
@RestController
public class BookRentalController {
	// Services this controller depends on
	private BookRentalService bookRentalService;

	private static final Logger log = LoggerFactory.getLogger(BookRentalController.class);

	/**
	 * @param bookRentalService
	 */
	public BookRentalController(BookRentalService bookRentalService) {
		super();

		this.bookRentalService = bookRentalService;
	}

	@PutMapping(value = "/loanBook/{isbn}/to/{borrowerId}")
	public void loanBook(@PathVariable long isbn, @PathVariable long borrowerId) {
		Optional<Rental> maybeRental = bookRentalService.loanBook(IsbnFactory.create(isbn), borrowerId);

		if (maybeRental.isEmpty()) {
			// Usually we'd handle this... but time...
		} else {
			log.info("Recorded Rental:{}", maybeRental.get());
		}
	}

	@GetMapping(value = "/getRentals/{borrowerId}")
	public RentalListWrapper getRentals(@PathVariable long borrowerId) {
		return new RentalListWrapper(bookRentalService.findRentals(borrowerId));
	}

	@GetMapping(value = "/getAllRentals")
	public RentalListWrapper getAllRentals() {
		return new RentalListWrapper(bookRentalService.findAllRentals());
	}
}
