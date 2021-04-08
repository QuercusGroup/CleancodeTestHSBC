package uk.co.hsbc.library.handler;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.hsbc.library.exception.InvalidLibraryIdException;
import uk.co.hsbc.library.model.Library;
import uk.co.hsbc.library.repository.LibraryRepository;
import uk.co.hsbc.library.service.model.LibraryInfo;

import java.util.List;

@Component
public class LibraryHandler {

	@Autowired
	private LibraryRepository repository;
	
	public Library addLibrary(LibraryInfo libraryModel) {
		Library createdLibrary = new Library();
		createdLibrary.setSelf(false);
		update(libraryModel, createdLibrary);
		return repository.save(createdLibrary);
	}

	public List<Library> getAllLibraries() {
		List<Library> collection = Lists.newArrayList();
		repository.findAll().forEach(collection::add);
		return collection;
	}

	public Library getLibrary() {
		return repository.findLibrary();
	}

	public Library getLibrary(long id) throws InvalidLibraryIdException {
		return repository.findById(id).orElseThrow(InvalidLibraryIdException::new);
	}

	public Library updateLibrary(long id, LibraryInfo libraryModel) throws InvalidLibraryIdException {
		Library library = getLibrary(id);
		update(libraryModel, library);
		return repository.save(library);
	}

	private void update(LibraryInfo source, Library target) {
		target.setName(source.getName());
		target.setAddress(source.getAddress());
		target.setEmail(source.getEmail());
		target.setPhoneNumber(source.getPhoneNumber());
	}
}
