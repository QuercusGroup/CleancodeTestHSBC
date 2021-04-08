/**
 * 
 */
package com.example.libman.domain.rental;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.libman.domain.book.Isbn;

/**
 * Captures the rental of a Book by a Borrower. Modelled as a separate entity
 * (as opposed to a Many-Many relationship) for greater flexibility w.r.t. later
 * auditing, search, etc.
 * 
 * @author David Pellatt
 */
@Entity
public class Rental implements Serializable {
	// For a Rental, we assume there's no natural business key so use an
	// auto-generated id.
	@Id
	@GeneratedValue
	private long rentalId;

	private Long borrowerId;
	private Isbn bookIsbn;
	private LocalDate startDate;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1609785289258290969L;

	/**
	 * Default constructor for JPA use only
	 */
	protected Rental() {
		super();
	}

	public Long getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(Long borrowerId) {
		this.borrowerId = borrowerId;
	}

	public Isbn getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(Isbn bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (rentalId ^ (rentalId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rental other = (Rental) obj;
		if (rentalId != other.rentalId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rental [rentalId=" + rentalId + ", borrowerId=" + borrowerId + ", bookIsbn=" + bookIsbn + ", startDate="
				+ startDate + "]";
	}

}
