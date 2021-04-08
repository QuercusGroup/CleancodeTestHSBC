package uk.co.hsbc.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({
		Exception.class
	})
	public ResponseEntity handleAllExceptions(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}

	@ExceptionHandler({
		InvalidUserIdException.class,
		InvalidLibraryIdException.class,
		InvalidLoanIdException.class,
		InvalidRentalRecipientException.class,
		InvalidBookIdException.class
	})
	public ResponseEntity handle404Exceptions(Exception ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
