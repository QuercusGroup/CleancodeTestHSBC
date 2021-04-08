package hsbc.codetest.librarybookrentalms.data.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import hsbc.codetest.librarybookrentalms.bss.dto.BookDto;
import hsbc.codetest.librarybookrentalms.bss.dto.UserDto;

@Entity
@Table(name = "book_rentals")
@JsonInclude(Include.NON_EMPTY)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BookRentalEntity {
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "book_rentals_seq", sequenceName = "book_rentals_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_rentals_seq")
	private Long id;

	@Column(name = "book_id", nullable = false)
	private Long bookId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "rent_date")
	private Date rentDate = new Date();

	@Column(name = "return_date")
	private Date returnDate;

	@Column(name = "book_details")
	@Convert(converter = BookDto.class)
	private BookDto bookDetails = new BookDto();

	@Column(name = "user_details")
	@Convert(converter = UserDto.class)
	private UserDto userDetails = new UserDto();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public BookDto getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(BookDto bookDetails) {
		this.bookDetails = bookDetails;
	}

	public UserDto getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDto userDetails) {
		this.userDetails = userDetails;
	}

}
