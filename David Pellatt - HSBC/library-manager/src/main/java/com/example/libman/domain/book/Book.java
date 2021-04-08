package com.example.libman.domain.book;

import javax.persistence.Entity;

import com.example.libman.domain.AbstractEntity;

/**
 * An individual Book held in the Library. For the purposes of this demo, there
 * are unlimited copies of each Book available.
 * 
 * @author David Pellatt
 */
@Entity
public class Book extends AbstractEntity<Isbn> {
	private String title;

	/**
	 * Statics
	 */
	private static final long serialVersionUID = 1956274749517015649L;

	/**
	 * Default constructor for JPA use only
	 */
	protected Book() {
		super();
	}

	public Isbn getIsbn() {
		return super.getKey();
	}

	public void setIsbn(Isbn isbn) {
		super.setKey(isbn);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + getIsbn() + ", title='" + title + "']";
	}
}
