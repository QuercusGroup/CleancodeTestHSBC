package uk.co.hsbc.library.service.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.hsbc.library.exception.InvalidLibraryIdException;
import uk.co.hsbc.library.handler.LibraryHandler;
import uk.co.hsbc.library.model.Library;
import uk.co.hsbc.library.service.model.LibraryInfo;

@RestController
public class LibraryResource {

	@Autowired
	private LibraryHandler libraryHandler;

	@RequestMapping(
		method = RequestMethod.GET,
		path = "/libraries",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Object> getAllLibraries() {
		return ResponseEntity.ok(libraryHandler.getAllLibraries());
	}

	@RequestMapping(
		method = RequestMethod.GET,
		path = "/library",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Library> getThisLibrary() {
		return ResponseEntity.ok(libraryHandler.getLibrary());
	}

	@RequestMapping(
		method = RequestMethod.GET,
		path = "/library/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Library> getLibrary(@PathVariable("id") Long id) throws InvalidLibraryIdException {
		return ResponseEntity.ok(libraryHandler.getLibrary(id));
	}

	@RequestMapping(
		method = RequestMethod.POST,
		path = "/library",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Library> addLibrary(@RequestBody LibraryInfo libraryModel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryHandler.addLibrary(libraryModel));
	}

	@RequestMapping(
			method = RequestMethod.PUT,
			path = "/library/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Library> updateLibrary(@PathVariable("id") Long id, @RequestBody LibraryInfo libraryModel) throws InvalidLibraryIdException {
		return ResponseEntity.ok(libraryHandler.updateLibrary(id, libraryModel));
	}
}
