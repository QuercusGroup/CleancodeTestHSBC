package com.librarymanagementsystem;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.model.User;
import com.librarymanagementsystem.service.BookService;
import com.librarymanagementsystem.service.UserService;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class LibraryManagementSystemApplication
{
    @Autowired
    private BookService bookService;
    
    @Autowired
    private UserService userService;

    public static void main( String[] args ){
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

    @PostConstruct
    public void initApplication() throws IOException {
    	bookService.addBook(new Book("101","The Sentinel","Lee Child","Corgi Books","Available"));
    	bookService.addBook(new Book("102","The Midnight Library","Matt Haig","Canongate Books Ltd","Available"));
    	bookService.addBook(new Book("103","Tap to Tidy","Stacey Solomon","Ebury Press","Available"));
    	bookService.addBook(new Book("104","Hear Me Out","Sarah","Ebury Press","Available"));
    	bookService.addBook(new Book("105","The Darkest Evening","Ann Cleeves","Pan Books","Available"));

    	userService.addUser(new User("1001","Joe","London"));
    	userService.addUser(new User("1002","Sam","Oxford"));
    	userService.addUser(new User("1003","Mike","Cambridge"));
    }
}

