package uk.co.hsbc.library.unit.handler;

import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.hsbc.library.LibraryApi;
import uk.co.hsbc.library.config.BookCategory;
import uk.co.hsbc.library.config.BookClassification;
import uk.co.hsbc.library.config.BookLanguage;
import uk.co.hsbc.library.handler.BookHandler;
import uk.co.hsbc.library.model.Book;
import uk.co.hsbc.library.service.model.BookInfo;

import java.time.Instant;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
	LibraryApi.class,
	Book.class,
	BookHandler.class
}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("classpath:test.properties")
public class BookHandlerTest {

	@Autowired private BookHandler bookHandler;

	@Test
	public void test_GivenBasicBookDetails_WhenAddedToTheCatalogue_ThenTheBookShouldBePresent() throws InterruptedException {
		BookInfo info = new BookInfo();
		info.setTitle("The beginning of everything");
		info.setAuthor("Unknown author");
		info.setPublishedDate(Instant.now());
		info.setLanguage(BookLanguage.ENGLISH);
		info.setClassification(BookClassification.A);
		info.setGenre(BookCategory.JOURNAL);
		info.setCopiesAvailable(0);
		Book book = bookHandler.addBook(info);
		Assert.assertThat(book, IsNull.notNullValue());
		Assert.assertThat(book.getUuid(), IsNull.notNullValue());
	}
}