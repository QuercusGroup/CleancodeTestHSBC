package com.librarymanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagementsystem.model.BorrowBook;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, Integer> {
	List<BorrowBook> findByIsbnAndUserId(String isbn, String userId);
}
