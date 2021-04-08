package hsbc.codetest.librarybookrentalms.bss.dto;

import java.sql.Date;

import javax.persistence.AttributeConverter;

import hsbc.codetest.librarybookrentalms.utils.JsonUtil;

public class BookDto implements AttributeConverter<BookDto, String>{
	private Long id;
	private String title;
	private String writer;

	private Date publishDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String convertToDatabaseColumn(BookDto attribute) {
		return JsonUtil.mapToString(attribute);
	}

	@Override
	public BookDto convertToEntityAttribute(String dbData) {
		return dbData == null ? new BookDto() : JsonUtil.mapToObject(dbData, BookDto.class);
	}

}
