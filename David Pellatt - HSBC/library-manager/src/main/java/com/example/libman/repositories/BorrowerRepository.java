/**
 * 
 */
package com.example.libman.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.libman.domain.borrower.Borrower;

/**
 * Simple CrudRepository to maintain our Borrowers.
 */
public interface BorrowerRepository extends CrudRepository<Borrower, Long> {

}
