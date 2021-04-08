package hsbc.codetest.librarybookrentalms.bss;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hsbc.codetest.librarybookrentalms.bss.dto.BookDto;
import hsbc.codetest.librarybookrentalms.bss.dto.BookRentalDto;
import hsbc.codetest.librarybookrentalms.bss.dto.UserDto;
import hsbc.codetest.librarybookrentalms.bss.exception.BookAlreadyRented;
import hsbc.codetest.librarybookrentalms.bss.exception.RecordNotFound;
import hsbc.codetest.librarybookrentalms.clients.BookServiceClient;
import hsbc.codetest.librarybookrentalms.clients.UserServiceClient;
import hsbc.codetest.librarybookrentalms.data.BookRentalDataService;
import hsbc.codetest.librarybookrentalms.data.entity.BookRentalEntity;

@Service
public class BookRentalService {

	@Autowired
	private BookRentalDataService bookRentalDataService;

	@Autowired
	private BookRentalDataMapper bookRentalDataMapper;
	
	@Autowired
	private UserServiceClient userServiceClient;
	
	@Autowired
	private BookServiceClient bookServiceClient;

	public BookRentalDto rentBook(Long bookId, Long userId) {
		//get user, if not found throws record not found exception
		UserDto user = userServiceClient.getById(userId);
		
		//get book, if not found throws record not found exception
		BookDto book = bookServiceClient.getById(bookId);
		
	
		//check if book is already rented
		if(bookRentalDataService.existsByBookIdAndReturnDateIsNull(bookId)) {
			throw new BookAlreadyRented(bookId.toString());
		}
		
		//create book rental object
		BookRentalEntity bookRental = new BookRentalEntity();
		bookRental.setUserDetails(user);
		bookRental.setBookDetails(book);
		bookRental.setUserId(userId);
		bookRental.setBookId(bookId);
		
		//save it
		bookRental = bookRentalDataService.save(bookRental);
				
		//map to DTO and return it
		return bookRentalDataMapper.mapBookRentalEntityToBookRentalDto(bookRental);		
	}
	public BookRentalDto returnBook(Long bookRentalId) {
		//get record, if not found throws record not found exception
		BookRentalEntity bookRental = bookRentalDataService.findById(bookRentalId).orElseThrow(() -> new RecordNotFound(bookRentalId.toString()));
		
		//update return date
		bookRental.setReturnDate(new Date());
		bookRental = bookRentalDataService.save(bookRental);
		
		return bookRentalDataMapper.mapBookRentalEntityToBookRentalDto(bookRental);
	}
	
	public List<BookRentalDto> findByBookId(Long bookId) {
		List<BookRentalDto> list = new ArrayList<>();
		bookRentalDataService.findByBookId(bookId).forEach(p-> list.add(bookRentalDataMapper.mapBookRentalEntityToBookRentalDto(p)));
		return list;
	}
	
	public List<BookRentalDto> findByUserId(Long userId) {
		List<BookRentalDto> list = new ArrayList<>();
		bookRentalDataService.findByUserId(userId).forEach(p-> list.add(bookRentalDataMapper.mapBookRentalEntityToBookRentalDto(p)));
		return list;
	}
	
	public BookRentalDto getById(Long id) {
		BookRentalEntity book = bookRentalDataService.findById(id).orElseThrow(() -> new RecordNotFound(id.toString()));
		return bookRentalDataMapper.mapBookRentalEntityToBookRentalDto(book);
	}
	
	public List<BookRentalDto> getAllRecords() {
		List<BookRentalDto> list = new ArrayList<>();
		bookRentalDataService.findAll().forEach(p-> list.add(bookRentalDataMapper.mapBookRentalEntityToBookRentalDto(p)));
		return list;
	}

}
