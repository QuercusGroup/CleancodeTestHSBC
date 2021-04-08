package com.example.libman.domain.book;

/**
 * Simple Factory class to create instances of Book.
 * 
 * @author David Pellatt
 */
public class BookFactory {

	/**
	 * @param isbn
	 * @param title
	 * @return Book
	 */
	public static final Book create(Isbn isbn, String title) {
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		return book;
	}

	private BookFactory() {
		// No instances permitted
	}
}
