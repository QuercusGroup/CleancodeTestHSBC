package uk.co.hsbc.library.service.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.hsbc.library.exception.InvalidLoanIdException;
import uk.co.hsbc.library.exception.InvalidRentalRecipientException;
import uk.co.hsbc.library.handler.LoanHandler;
import uk.co.hsbc.library.model.Loan;
import uk.co.hsbc.library.service.model.LoanInfo;

@RestController
public class RentalResource {

	@Autowired
	private LoanHandler loanHandler;

	@RequestMapping(
		method = RequestMethod.GET,
		path = "/rentals",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Object> getAllRentals(@RequestParam(value = "includeReturned", required = false, defaultValue = "false") boolean includeReturned) {
		return ResponseEntity.ok(loanHandler.getAllLoans(includeReturned));
	}

	@RequestMapping(
		method = RequestMethod.GET,
		path = "/rental/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Loan> getRental(@PathVariable("id") Long id) throws InvalidLoanIdException {
		return ResponseEntity.ok(loanHandler.getLoan(id));
	}

	@RequestMapping(
		method = RequestMethod.POST,
		path = "/rental",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Loan> addRental(@RequestBody LoanInfo loanModel) throws InvalidRentalRecipientException {
		return ResponseEntity.status(HttpStatus.CREATED).body(loanHandler.addLoan(loanModel));
	}

	@RequestMapping(
		method = RequestMethod.PUT,
		path = "/rental/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Loan> updateRental(@PathVariable("id") Long id, @RequestBody LoanInfo loanModel) throws InvalidLoanIdException {
		return ResponseEntity.ok(loanHandler.updateLoan(id, loanModel));
	}
}
