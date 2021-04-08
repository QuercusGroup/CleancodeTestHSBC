package uk.co.hsbc.library.handler;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.hsbc.library.exception.InvalidLibraryIdException;
import uk.co.hsbc.library.exception.InvalidLoanIdException;
import uk.co.hsbc.library.exception.InvalidRentalRecipientException;
import uk.co.hsbc.library.exception.InvalidUserIdException;
import uk.co.hsbc.library.model.Loan;
import uk.co.hsbc.library.repository.LoanRepository;
import uk.co.hsbc.library.service.model.LoanInfo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class LoanHandler {

	@Autowired private LoanRepository repository;
	@Autowired private UserHandler userHandler;
	@Autowired private LibraryHandler libraryHandler;

	public Loan addLoan(LoanInfo loanModel) throws InvalidRentalRecipientException {
		Loan createdLoan = new Loan();
		if (loanModel.getFromDate() == null) {
			loanModel.setFromDate(Instant.now());
		}
		if (loanModel.getDueDate() == null) {
			loanModel.setDueDate( loanModel.getFromDate().plus(7, ChronoUnit.DAYS) );
		}

		try {
			userHandler.getUser(loanModel.getMemberId());
		} catch (InvalidUserIdException iuie) {
			try {
				libraryHandler.getLibrary(loanModel.getLibraryId());
			} catch (InvalidLibraryIdException ilie) {
				throw new InvalidRentalRecipientException();
			}
		}

		update(loanModel, createdLoan);
		return repository.save(createdLoan);
	}

	public List<Loan> getAllLoans(boolean includeReturnedItems) {
		List<Loan> loans = Lists.newArrayList();
		if (includeReturnedItems)
			repository.findAll().forEach(loans::add);
		else
			repository.findCurrentLoans().forEach(loans::add);

		return loans;
	}

	public Loan getLoan(long id) throws InvalidLoanIdException {
		return repository.findById(id).orElseThrow(InvalidLoanIdException::new);
	}

	public Loan updateLoan(long id, LoanInfo loanModel) throws InvalidLoanIdException {
		Loan loan = getLoan(id);
		update(loanModel, loan);
		return repository.save(loan);
	}

	private void update(LoanInfo source, Loan target) {
		target.setBookUuid(source.getBookUuid());
		target.setFromDate(source.getFromDate());
		target.setDueDate(source.getDueDate());
		target.setReturnDate(source.getReturnDate());
		target.setChargeDue(source.getChargeDue());
		target.setChargePaid(source.getChargePaid());
		target.setMemberId(source.getMemberId());
		target.setLibraryId(source.getLibraryId());
	}
}
