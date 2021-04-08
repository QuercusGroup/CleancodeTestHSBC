package hsbc.codetest.librarybookrentalms.bss.dto;

import java.util.Date;

public class BookRentalDto {
	private Long id;
	private Long bookId;
	private Long userId;
	private Date rentDate;
	private Date returnDate;
	private BookDto bookDetails = new BookDto();
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

	public BookRentStatus getBookRentStatus() {
		return this.getReturnDate() == null ? BookRentStatus.RENTED : BookRentStatus.RETURNED;
	}

}
