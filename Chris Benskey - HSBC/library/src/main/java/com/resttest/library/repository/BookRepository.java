/*
 * Unpublished work, Copyright (c) 2021 Shutterfly, Inc., All Rights Reserved
 */

package com.resttest.library.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.resttest.library.model.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("FROM Book b WHERE b.ISBN = :ISBN")
    Optional<Book> findByISBNForUpdate(@Param("ISBN") String ISBN);

}
