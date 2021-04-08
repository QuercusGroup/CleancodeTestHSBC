package uk.co.hsbc.library.service.model;

import lombok.Getter;
import lombok.Setter;
import uk.co.hsbc.library.config.BookCategory;
import uk.co.hsbc.library.config.BookClassification;
import uk.co.hsbc.library.config.BookLanguage;

import java.time.Instant;

@Getter
@Setter
public class BookInfo {

	private String uuid;
	private String author;
	private String title;
	private String description;
	private String isbn;
	private Instant publishedDate;
	private String coverImageData;
	private BookLanguage language;
	private BookCategory genre;
	private BookClassification classification;
	private int copiesAvailable;
}
