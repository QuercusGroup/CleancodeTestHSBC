package com.example.libman.domain.book;

/**
 * @author David Pellatt
 */
public class IsbnFactory {
	/**
	 * @param value
	 * @return Isbn
	 */
	public static final Isbn create(long value) {
		Isbn isbn = new Isbn();
		isbn.setValue(value);
		return isbn;
	}

	private IsbnFactory() {
		// No instances permitted
	}
}
