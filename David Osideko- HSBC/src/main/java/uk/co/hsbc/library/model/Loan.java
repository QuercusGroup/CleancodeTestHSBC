package uk.co.hsbc.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Getter
@Setter
@Entity
public class Loan {

	@Id @GeneratedValue private Long id;
	private String bookUuid;
	private Instant fromDate;
	private Instant dueDate;
	private Instant returnDate;
	private Double chargeDue;
	private Double chargePaid;

	private Long memberId; // Member loan
	private Long libraryId; // Inter-library loan
}
