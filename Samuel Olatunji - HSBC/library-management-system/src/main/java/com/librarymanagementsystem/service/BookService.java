package com.librarymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public void addBook(Book book){
		bookRepository.save(book);
    }

    public Book getBook(String isbn){
        return bookRepository.findOne(isbn);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Boolean isBookAvailable(String isbn) {
        return bookRepository.exists(isbn);
    }

    public void removeBook(String isbn) {
    	bookRepository.delete(isbn);
    }
    
    public String getBookStatus(String isbn) {
    	Book book = bookRepository.findOne(isbn);
    	return book.getStatus();
    }
    
    public void updateStatus(String isbn, String status) {
    	Book book = bookRepository.findOne(isbn);
    	book.setStatus(status);
    	bookRepository.save(book);
    }
    public List<Book> getBookByStatus(String status) {
    	return bookRepository.findByStatus(status);
    }
 }
