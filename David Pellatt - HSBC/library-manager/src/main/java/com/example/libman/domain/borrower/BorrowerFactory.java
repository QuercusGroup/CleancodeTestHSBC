/**
 * 
 */
package com.example.libman.domain.borrower;

/**
 * Factory to create Borrower instances.
 * 
 * @author David Pellatt
 */
public final class BorrowerFactory {
	/**
	 * @param id
	 * @param name
	 * @return Borrower
	 */
	public static final Borrower create(Long id, String name) {
		Borrower borrower = new Borrower();
		borrower.setKey(id);
		borrower.setName(name);
		return borrower;
	}

	private BorrowerFactory() {
		// No instances permitted
	}
}
