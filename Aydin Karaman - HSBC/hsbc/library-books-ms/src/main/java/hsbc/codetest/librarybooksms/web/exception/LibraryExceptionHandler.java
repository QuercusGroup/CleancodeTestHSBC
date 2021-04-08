package hsbc.codetest.librarybooksms.web.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import hsbc.codetest.librarybooksms.web.BookResource;
import hsbc.codetest.librarybooksms.web.exception.dto.ResponseMesssage;

@ControllerAdvice(assignableTypes = BookResource.class)
public class LibraryExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ResponseMesssage> generalException(Exception ex, WebRequest request) {
		String message = ex.getMessage();
		if (message == null) {
			message = ex.getClass().getSimpleName();
		}
		ResponseMesssage errors = new ResponseMesssage(HttpStatus.BAD_REQUEST.value(), message);
		logger.error(message);
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("status", status.value());

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);
		return new ResponseEntity<>(body, headers, status);

	}

}
