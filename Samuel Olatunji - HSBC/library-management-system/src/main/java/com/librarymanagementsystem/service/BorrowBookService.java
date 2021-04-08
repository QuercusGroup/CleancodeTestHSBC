package com.librarymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagementsystem.model.BorrowBook;
import com.librarymanagementsystem.repository.BorrowBookRepository;

@Service
public class BorrowBookService {

	@Autowired
	BorrowBookRepository borrowBookRepository;
	
	@Autowired
	BookService bookService;
	
	public void borrowBook(BorrowBook borrowBook) {
		borrowBookRepository.save(borrowBook);
		bookService.updateStatus(borrowBook.getIsbn(), "Borrow");
	}
	
	public void returnBook(String isbn) {
		bookService.updateStatus(isbn, "Available");
	}
	
	public boolean checkBorrowExist(String isbn, String userId) {
		BorrowBook book = getBookByIsbnAndUserId(isbn, userId);
		if(book != null) {
			return true;
		}
		return false;
	}
	
	public BorrowBook getBookByIsbnAndUserId(String isbn, String userId) {
		List<BorrowBook> list = borrowBookRepository.findByIsbnAndUserId(isbn, userId);
		if(list.size() > 0) return list.get(0);
		return null;
	}
}
