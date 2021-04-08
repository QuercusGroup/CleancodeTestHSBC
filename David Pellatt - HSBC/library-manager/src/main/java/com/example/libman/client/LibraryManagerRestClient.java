/**
 * 
 */
package com.example.libman.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.libman.domain.borrower.Borrower;
import com.example.libman.domain.rental.Rental;
import com.example.libman.dto.RentalListWrapper;

/**
 * As a librarian, I'm not over-impressed that my IT department expect me to use
 * a REST API to manage my library... so I'll use this noddy client to show me
 * what it does.
 * 
 * @author David Pellatt
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })
public class LibraryManagerRestClient {
	private static final String HOST = "http://localhost:8080";
	private static final Logger log = LoggerFactory.getLogger(LibraryManagerRestClient.class);

	/**
	 * Create the Client App as a simple Spring Boot app with no Web/Application
	 * server.
	 */
	public static void main(String[] args) {
		new SpringApplicationBuilder(LibraryManagerRestClient.class).web(WebApplicationType.NONE).run(args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			log.info("Server Status:{}", restTemplate.getForObject(HOST + "/status", String.class));
			log.info("Loading Books into the Library");

			populateBooks(restTemplate);
			
			log.info("Loading Borrowers");

			populateBorrowers(restTemplate);

			heading("Loaning some Books");

			restTemplate.put(HOST + "/loanBook/101/to/3", null);
			restTemplate.put(HOST + "/loanBook/1/to/2", null);
			restTemplate.put(HOST + "/loanBook/99/to/1", null);

			heading("Finding the Books Rented to Borrower 'Nita'");

			println(restTemplate, restTemplate.getForObject(HOST + "/getRentals/3", RentalListWrapper.class));

			heading("Finding all Books Rented");

			println(restTemplate, restTemplate.getForObject(HOST + "/getAllRentals", RentalListWrapper.class));
		};
	}

	private void heading(String msg) {
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println(msg);
		System.out.println();
	}

	/**
	 * Print the details of the given Rentals
	 */
	private void println(RestTemplate restTemplate, RentalListWrapper rentalsWrapper) {
		List<Rental> rentals = rentalsWrapper.getList();

		rentals.forEach(rental -> {
			println(restTemplate, rental);
		});
	}

	/**
	 * Print the details of the given Rental
	 */
	private void println(RestTemplate restTemplate, Rental rental) {
		// Query the server for Borrower details
		Borrower borrower = restTemplate.getForObject(HOST + "/getBorrower/" + Long.toString(rental.getBorrowerId()),
				Borrower.class);

		System.out.println(borrower.getName() + " has borrowed a copy of Book with ISBN:" + rental.getBookIsbn());
	}

	/**
	 * Create a set of books to demo the Library.
	 */
	private void populateBooks(RestTemplate restTemplate) {
		addBook(restTemplate, 1, "Fenders");
		addBook(restTemplate, 99, "Gibsons");
		addBook(restTemplate, 101, "Martins");
	}

	/**
	 * Post a single book to the Library Server
	 */
	private void addBook(RestTemplate restTemplate, long isbn, String title) {
		restTemplate.put(HOST + "/addBookToLibrary/" + Long.toString(isbn) + '/' + title, null);
	}

	/**
	 * Create a set of Borrowers to demo the Library.
	 */
	private void populateBorrowers(RestTemplate restTemplate) {
		addBorrower(restTemplate, 1, "Jimi");
		addBorrower(restTemplate, 2, "Joe");
		addBorrower(restTemplate, 3, "Nita");
	}

	/**
	 * Post a single Borrower to the Library Server
	 */
	private void addBorrower(RestTemplate restTemplate, long id, String name) {
		restTemplate.put(HOST + "/addBorrowerToLibrary/" + Long.toString(id) + '/' + name, null);
	}
}
