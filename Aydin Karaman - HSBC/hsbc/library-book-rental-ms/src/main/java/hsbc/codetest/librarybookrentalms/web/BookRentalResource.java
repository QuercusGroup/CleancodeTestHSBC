package hsbc.codetest.librarybookrentalms.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import hsbc.codetest.librarybookrentalms.bss.BookRentalService;
import hsbc.codetest.librarybookrentalms.bss.dto.BookRentalDto;

@RestController
public class BookRentalResource {

	@Autowired
	private BookRentalService bookRentalService;

	@PostMapping("/book/{bookId}/user/{userId}")
	public BookRentalDto rentBook(@PathVariable Long bookId, @PathVariable Long userId) {
		return bookRentalService.rentBook(bookId, userId);
	}

	@PutMapping("/{id}")
	public BookRentalDto returnBook(@PathVariable Long id) {
		return bookRentalService.returnBook(id);
	}

	@GetMapping("/book/{bookId}")
	public List<BookRentalDto> findRentalHistoryOfBook(@PathVariable Long bookId) {
		return bookRentalService.findByBookId(bookId);
	}

	@GetMapping("/user/{userId}")
	public List<BookRentalDto> findRentalHistoryOfUser(@PathVariable Long userId) {
		return bookRentalService.findByUserId(userId);
	}

	@GetMapping
	public List<BookRentalDto> getAllRecords() {
		return bookRentalService.getAllRecords();
	}

	@GetMapping("/{id}")
	public BookRentalDto getById(@PathVariable Long id) {
		return bookRentalService.getById(id);
	}
}
