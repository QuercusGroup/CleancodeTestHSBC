package com.librarymanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.librarymanagementsystem.model.Book;

@Component
public interface BookRepository extends JpaRepository<Book, String> {
	List<Book> findByStatus(String status);
	
}
