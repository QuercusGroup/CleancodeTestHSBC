package uk.co.hsbc.library.service.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.hsbc.library.exception.InvalidUserIdException;
import uk.co.hsbc.library.handler.UserHandler;
import uk.co.hsbc.library.model.User;
import uk.co.hsbc.library.service.model.UserInfo;

@RestController
public class MembershipResource {

	@Autowired
	private UserHandler userHandler;

	@RequestMapping(
		method = RequestMethod.GET,
		path = "/members",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Object> getAllMembers() {
		return ResponseEntity.ok(userHandler.getAllUsers());
	}

	@RequestMapping(
		method = RequestMethod.GET,
		path = "/member/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<User> getMember(@PathVariable("id") Long id) throws InvalidUserIdException {
		return ResponseEntity.ok(userHandler.getUser(id));
	}

	@RequestMapping(
			method = RequestMethod.POST,
			path = "/member",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<User> addMember(@RequestBody UserInfo userModel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userHandler.addUser(userModel));
	}

	@RequestMapping(
		method = RequestMethod.PUT,
		path = "/member/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<User> updateMember(@PathVariable("id") Long id, @RequestBody UserInfo userModel) throws InvalidUserIdException {
		return ResponseEntity.ok(userHandler.updateUser(id, userModel));
	}

	@RequestMapping(
		method = RequestMethod.DELETE,
		path = "/member/{id}"
	)
	public ResponseEntity deleteMember(@PathVariable("id") Long id) throws InvalidUserIdException {
		userHandler.deleteUser(id);
		return ResponseEntity.ok().build();
	}
}
