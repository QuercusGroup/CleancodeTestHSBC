package hsbc.codetest.librarybooksms.bss.dto;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

public class BookDto {
	private Long id;
	
	@NotBlank(message = "Please provide a title")
	private String title;
	
	@NotBlank(message = "Please provide a writer")
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

}
