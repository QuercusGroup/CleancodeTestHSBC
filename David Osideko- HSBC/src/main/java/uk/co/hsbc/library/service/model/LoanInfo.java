package uk.co.hsbc.library.service.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class LoanInfo {

	private String bookUuid;
	private Instant fromDate;
	private Instant dueDate;
	private Instant returnDate;
	private Double chargeDue;
	private Double chargePaid;

	private Long memberId; // Member loan
	private Long libraryId; // Inter-library loan
}
