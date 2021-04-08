package com.example.libman.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.libman.domain.rental.Rental;

/**
 * Simple CrudRepository to maintain our Rentals.
 */
public interface RentalRepository extends CrudRepository<Rental, Long> {
	/**
	 * Find all the rentals for a specific borrower.
	 */
	List<Rental> findByBorrowerId(Long borrowerId);
}
