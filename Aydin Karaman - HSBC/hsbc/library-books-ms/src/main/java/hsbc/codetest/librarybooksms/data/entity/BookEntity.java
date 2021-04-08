package hsbc.codetest.librarybooksms.data.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "books")
@Where(clause = "record_status != 'DELETED'")
@JsonInclude(Include.NON_EMPTY)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BookEntity {
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "books_seq", sequenceName = "books_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_seq")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "writer_name", nullable = false)
	private String writer;

	@Column(name = "publishDate")
	private Date publishDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "record_status")
	private RecordStatusEnum recordStatus = RecordStatusEnum.ACTIVE;

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

	public RecordStatusEnum getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(RecordStatusEnum recordStatus) {
		this.recordStatus = recordStatus;
	}

}
