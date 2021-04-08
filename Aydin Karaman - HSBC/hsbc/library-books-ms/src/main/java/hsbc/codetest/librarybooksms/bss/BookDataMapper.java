package hsbc.codetest.librarybooksms.bss;

import org.springframework.stereotype.Service;

import hsbc.codetest.librarybooksms.bss.dto.BookDto;
import hsbc.codetest.librarybooksms.data.entity.BookEntity;

@Service
public class BookDataMapper {

	public BookEntity mapBookDtoToBookEntity(BookDto book) {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setId(book.getId());
		bookEntity.setPublishDate(book.getPublishDate());
		bookEntity.setTitle(book.getTitle());
		bookEntity.setWriter(book.getWriter());

		return bookEntity;
	}

	public BookDto mapBookEntityToBookDto(BookEntity book) {
		BookDto bookDto = new BookDto();
		bookDto.setId(book.getId());
		bookDto.setPublishDate(book.getPublishDate());
		bookDto.setTitle(book.getTitle());
		bookDto.setWriter(book.getWriter());

		return bookDto;
	}

	public BookEntity mapBookDtoToBookEntity(BookDto book, BookEntity bookEntity) {
		bookEntity.setPublishDate(book.getPublishDate());
		bookEntity.setTitle(book.getTitle());
		bookEntity.setWriter(book.getWriter());

		return bookEntity;
	}

}
