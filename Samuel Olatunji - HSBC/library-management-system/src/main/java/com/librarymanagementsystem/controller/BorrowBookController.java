package com.librarymanagementsystem.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagementsystem.model.BorrowBook;
import com.librarymanagementsystem.service.BookService;
import com.librarymanagementsystem.service.BorrowBookService;
import com.librarymanagementsystem.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "BorrowBook", description = "Operations about Borrow Book")
public class BorrowBookController {

	@Autowired
	private BorrowBookService borrowBookService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/borrow-book",
    method = RequestMethod.POST,
    produces = APPLICATION_JSON_VALUE,
    consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Borrow Book")
	public ResponseEntity borrowBook(@RequestBody BorrowBook borrowBook) {
		if(checkUserExists(borrowBook.getUserId())
				&& checkBookExists(borrowBook.getIsbn())) {
			
			if(checkBookStatus(borrowBook.getIsbn())) {
				borrowBookService.borrowBook(borrowBook);
				return new ResponseEntity(borrowBook, HttpStatus.CREATED);
			} 
			return new ResponseEntity("Book is already borrowed to other user", HttpStatus.OK);
			
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/return-book/{isbn}/{userId}",
    method = RequestMethod.POST,
    produces = APPLICATION_JSON_VALUE,
    consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Return Book")
	public ResponseEntity returnBook(@PathVariable String isbn, @PathVariable String userId) {
		if(borrowBookService.checkBorrowExist(isbn, userId)) {
			borrowBookService.returnBook(isbn);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	private boolean checkUserExists(String userId) {
		if(userService.isUserAvailable(userId)) return true;
		return false;
	}
	
	private boolean checkBookExists(String isbn) {
		if(bookService.isBookAvailable(isbn)) return true;
		return false;
	}

	private boolean checkBookStatus(String isbn) {
		if(!"borrow".equals(bookService.getBookStatus(isbn))) return true;
		return false;
	}
}
