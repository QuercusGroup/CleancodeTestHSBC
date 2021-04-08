package hsbc.codetest.librarybookrentalms.bss;

import org.springframework.stereotype.Service;

import hsbc.codetest.librarybookrentalms.bss.dto.BookRentalDto;
import hsbc.codetest.librarybookrentalms.data.entity.BookRentalEntity;

@Service
public class BookRentalDataMapper {

	public BookRentalEntity mapBookRentalDtoToBookRentalEntity(BookRentalDto bookRentalDetail) {
		BookRentalEntity booRentalkEntity = new BookRentalEntity();
		booRentalkEntity.setId(bookRentalDetail.getId());
		booRentalkEntity.setBookDetails(bookRentalDetail.getBookDetails());
		booRentalkEntity.setBookId(bookRentalDetail.getBookId());
		booRentalkEntity.setUserDetails(bookRentalDetail.getUserDetails());
		booRentalkEntity.setUserId(bookRentalDetail.getUserId());
		booRentalkEntity.setRentDate(bookRentalDetail.getRentDate());
		booRentalkEntity.setReturnDate(bookRentalDetail.getReturnDate());
		return booRentalkEntity;
	}

	public BookRentalDto mapBookRentalEntityToBookRentalDto(BookRentalEntity booRentalkEntity) {
		BookRentalDto booRentalk = new BookRentalDto();
		booRentalk.setId(booRentalkEntity.getId());
		booRentalk.setBookDetails(booRentalkEntity.getBookDetails());
		booRentalk.setBookId(booRentalkEntity.getBookId());
		booRentalk.setUserDetails(booRentalkEntity.getUserDetails());
		booRentalk.setUserId(booRentalkEntity.getUserId());
		booRentalk.setRentDate(booRentalkEntity.getRentDate());
		booRentalk.setReturnDate(booRentalkEntity.getReturnDate());
		return booRentalk;
	}

}
