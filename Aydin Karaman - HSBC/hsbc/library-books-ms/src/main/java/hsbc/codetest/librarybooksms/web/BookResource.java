package hsbc.codetest.librarybooksms.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hsbc.codetest.librarybooksms.bss.BookService;
import hsbc.codetest.librarybooksms.bss.dto.BookDto;

@RestController
public class BookResource {

	@Autowired
	private BookService bookService;

	@PostMapping
	public BookDto saveBook(@Valid @RequestBody BookDto book) {
		return bookService.saveBook(book);
	}

	@PutMapping
	public BookDto updateOrCreateBook(@Valid @RequestBody BookDto book) {
		return bookService.updateOrCreateBook(book);
	}
	
	@PatchMapping("/{id}/writer/{writer}")
	public BookDto updateBookWriter(@PathVariable Long id, @PathVariable String writer) {
		return bookService.updateBookWriter(id, writer);
	}	
	
	@DeleteMapping("/{id}")
	public BookDto deleteBook(@PathVariable Long id) {
		return bookService.deleteBook(id);
	}
	
	@DeleteMapping("/logical/{id}")
	public BookDto deleteBookLogical(@PathVariable Long id) {
		return bookService.deleteBookLogical(id);
	}
	
	@GetMapping
	public List<BookDto> getAllRecords() {
		return bookService.getAllRecords();
	}
	
	@GetMapping("/{id}")
	public BookDto getById(@PathVariable Long id) {
		return bookService.getById(id);
	}
}
