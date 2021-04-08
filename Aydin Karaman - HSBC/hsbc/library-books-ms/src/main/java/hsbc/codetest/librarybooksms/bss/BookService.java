package hsbc.codetest.librarybooksms.bss;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hsbc.codetest.librarybooksms.bss.dto.BookDto;
import hsbc.codetest.librarybooksms.bss.exception.RecordAlreadyExist;
import hsbc.codetest.librarybooksms.bss.exception.RecordNotFound;
import hsbc.codetest.librarybooksms.data.BookDataService;
import hsbc.codetest.librarybooksms.data.entity.BookEntity;
import hsbc.codetest.librarybooksms.data.entity.RecordStatusEnum;

@Service
public class BookService {

	@Autowired
	private BookDataService bookDataService;

	@Autowired
	private BookDataMapper bookDataMapper;

	public BookDto saveBook(BookDto book) {
		if (book.getId() != null && bookDataService.existsById(book.getId())) {
			throw new RecordAlreadyExist(book.getId().toString());
		}
		else {
			book.setId(null);
		}
		BookEntity bookEntity = bookDataMapper.mapBookDtoToBookEntity(book);

		BookEntity savedBookRecord = bookDataService.save(bookEntity);

		return bookDataMapper.mapBookEntityToBookDto(savedBookRecord);
	}

	public BookDto updateOrCreateBook(BookDto book) {
		BookEntity bookEntity;

		if (book.getId() != null) {
			bookEntity = bookDataService.findById(book.getId())
					.orElseThrow(() -> new RecordNotFound(book.getId().toString()));
			bookEntity = bookDataMapper.mapBookDtoToBookEntity(book, bookEntity);

		} else {
			bookEntity = bookDataMapper.mapBookDtoToBookEntity(book);
		}

		BookEntity savedBookRecord = bookDataService.save(bookEntity);
		return bookDataMapper.mapBookEntityToBookDto(savedBookRecord);
	}

	public BookDto updateBookWriter(Long id, String writer) {
		BookEntity book = bookDataService.findById(id).orElseThrow(() -> new RecordNotFound(id.toString()));
		book.setWriter(writer);
		bookDataService.save(book);
		return bookDataMapper.mapBookEntityToBookDto(book);
	}


	public BookDto deleteBook(Long id) {
		BookEntity book = bookDataService.findById(id).orElseThrow(() -> new RecordNotFound(id.toString()));
		bookDataService.delete(book);
		return bookDataMapper.mapBookEntityToBookDto(book);
	}

	public BookDto deleteBookLogical(Long id) {
		BookEntity book = bookDataService.findById(id).orElseThrow(() -> new RecordNotFound(id.toString()));
		book.setRecordStatus(RecordStatusEnum.DELETED);
		bookDataService.save(book);
		return bookDataMapper.mapBookEntityToBookDto(book);
	}

	public BookDto getById(Long id) {
		BookEntity book = bookDataService.findById(id).orElseThrow(() -> new RecordNotFound(id.toString()));
		return bookDataMapper.mapBookEntityToBookDto(book);
	}

	public List<BookDto> getAllRecords() {
		List<BookDto> list = new ArrayList<>();
		bookDataService.findAll().forEach(p-> list.add(bookDataMapper.mapBookEntityToBookDto(p)));
		return list;
	}

}
