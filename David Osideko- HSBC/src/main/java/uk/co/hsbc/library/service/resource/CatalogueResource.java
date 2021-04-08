package uk.co.hsbc.library.service.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.hsbc.library.exception.InvalidBookIdException;
import uk.co.hsbc.library.handler.BookHandler;
import uk.co.hsbc.library.model.Book;
import uk.co.hsbc.library.service.model.BookInfo;

@RestController
@RequestMapping("/catalogue")
public class CatalogueResource {

	@Autowired private BookHandler bookHandler;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCatalogue() {
		return ResponseEntity.ok(bookHandler.getAllBooks());
	}

	@RequestMapping(
		method = RequestMethod.GET,
		path = "/book/{uuid}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Book> getBook(@PathVariable("uuid") String uuid) throws InvalidBookIdException {
		return ResponseEntity.ok(bookHandler.getBook(uuid));
	}

	@RequestMapping(
		method = RequestMethod.POST,
		path = "/book",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Book> addBook(@RequestBody BookInfo bookModel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bookHandler.addBook(bookModel));
	}

	@RequestMapping(
		method = RequestMethod.PUT,
		path = "/book/{uuid}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Book> updateBook(@PathVariable("uuid") String uuid, @RequestBody BookInfo bookModel) throws InvalidBookIdException {
		return ResponseEntity.ok(bookHandler.updateBook(uuid, bookModel));
	}

	@RequestMapping(
		method = RequestMethod.DELETE,
		path = "/book/{uuid}"
	)
	public ResponseEntity deleteBook(@PathVariable("uuid") String uuid) throws InvalidBookIdException {
		bookHandler.deleteBook(uuid);
		return ResponseEntity.ok().build();
	}
}
