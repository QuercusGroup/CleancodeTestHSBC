/**
 * 
 */
package com.example.libman.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.libman.domain.book.Book;
import com.example.libman.domain.book.Isbn;

/**
 * Simple CrudRepository to maintain our books.
 */
public interface BookRepository extends CrudRepository<Book, Isbn> {

}
