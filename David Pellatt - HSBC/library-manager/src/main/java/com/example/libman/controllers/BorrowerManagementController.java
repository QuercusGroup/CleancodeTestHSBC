package com.example.libman.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.libman.domain.borrower.Borrower;
import com.example.libman.domain.borrower.BorrowerFactory;
import com.example.libman.services.BorrowerManagementService;

/**
 * Controller providing endpoints to manage Borrowers.
 * 
 * @author David Pellatt
 */
@RestController
public class BorrowerManagementController {
	// Services this controller depends on
	private BorrowerManagementService borrowerManagementService;

	private static final Logger log = LoggerFactory.getLogger(BorrowerManagementController.class);

	/**
	 * @param borrowerManagementService
	 */
	public BorrowerManagementController(BorrowerManagementService borrowerManagementService) {
		super();

		this.borrowerManagementService = borrowerManagementService;
	}

	@PutMapping(value = "/addBorrowerToLibrary/{borrowerId}/{name}")
	public void addBorrowerToLibrary(@PathVariable long borrowerId, @PathVariable String name) {
		Borrower borrower = borrowerManagementService.addBorrower(BorrowerFactory.create(borrowerId, name));

		log.info("Added Borrower to Library:{}", borrower);
	}

	@GetMapping(value = "/getBorrower/{borrowerId}")
	public Borrower getBorrower(@PathVariable long borrowerId) {
		Optional<Borrower> maybeBorrower = borrowerManagementService.getBorrower(borrowerId);

		if (maybeBorrower.isEmpty()) {
			// Return a 404 if there was no matching Borrower
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			return maybeBorrower.get();
		}
	}
}
