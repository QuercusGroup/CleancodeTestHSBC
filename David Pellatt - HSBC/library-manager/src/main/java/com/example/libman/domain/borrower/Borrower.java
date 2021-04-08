/**
 * 
 */
package com.example.libman.domain.borrower;

import javax.persistence.Entity;

import com.example.libman.domain.AbstractEntity;

/**
 * Simple class representing the borrower of a book.
 * 
 * @author David Pellatt
 */
@Entity
public class Borrower extends AbstractEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4002469750772340817L;

	private String name;

	/**
	 * Default constructor for JPA use only
	 */
	protected Borrower() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Borrower [name=" + name + "]";
	}
}
