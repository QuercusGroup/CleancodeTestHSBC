package uk.co.hsbc.library.handler;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.hsbc.library.exception.InvalidBookIdException;
import uk.co.hsbc.library.model.Book;
import uk.co.hsbc.library.repository.BookRepository;
import uk.co.hsbc.library.service.model.BookInfo;

import java.util.List;
import java.util.UUID;

@Component
public class BookHandler {

	@Autowired private BookRepository repository;

	public Book addBook(BookInfo bookModel) {
		Book createdBook = new Book();
		createdBook.setUuid(UUID.randomUUID().toString());
		update(bookModel, createdBook);
		return repository.save(createdBook);
	}

	public List<Book> getAllBooks() {
		List<Book> collection = Lists.newArrayList();
		repository.findAll().forEach(collection::add);
		return collection;
	}

	public Book getBook(String uuid) throws InvalidBookIdException {
		Book book = repository.findByUuid(uuid);
		if (book==null) throw new InvalidBookIdException();
		return book;
	}

	public Book updateBook(String uuid, BookInfo bookModel) throws InvalidBookIdException {
		Book book = getBook(uuid);
		update(bookModel, book);
		return repository.save(book);
	}

	public void deleteBook(String uuid) throws InvalidBookIdException {
		Book book = getBook(uuid);
		repository.delete(book);
	}

	private void update(BookInfo source, Book target) {
		target.setTitle(source.getTitle());
		target.setDescription(source.getDescription());
		target.setAuthor(source.getAuthor());
		target.setIsbn(source.getIsbn());
		target.setPublishedDate(source.getPublishedDate());
		target.setCoverImageData(source.getCoverImageData());
		target.setLanguage(source.getLanguage());
		target.setGenre(source.getGenre());
		target.setClassification(source.getClassification());
		target.setCopiesAvailable(source.getCopiesAvailable());
	}
}
