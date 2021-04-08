/**
 * 
 */
package com.example.libman.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.libman.domain.borrower.Borrower;
import com.example.libman.repositories.BorrowerRepository;

/**
 * Service to encapsulate any rules around managing borrowers. For this demo
 * it's just a facade for the repository but we could add rules here (like only
 * one account per person, etc.) in future versions.
 * 
 * @author David Pellatt
 */
@Service
public class BorrowerManagementService {
	private BorrowerRepository borrowerRepository;

	public BorrowerManagementService(BorrowerRepository bookRepository) {
		super();
		this.borrowerRepository = bookRepository;
	}

	public Borrower addBorrower(Borrower borrower) {
		return borrowerRepository.save(borrower);
	}

	public Iterable<Borrower> getAllBorrowers() {
		return borrowerRepository.findAll();
	}

	public Optional<Borrower> getBorrower(long borrowerId) {
		return borrowerRepository.findById(borrowerId);
	}
}
