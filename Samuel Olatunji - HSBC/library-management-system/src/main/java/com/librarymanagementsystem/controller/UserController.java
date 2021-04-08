package com.librarymanagementsystem.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagementsystem.model.User;
import com.librarymanagementsystem.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "User", description = "Operations about Users")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get a user using its ID")
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		User result = userService.getUser(id);
		HttpStatus httpStatus;
		if (result != null) {
			httpStatus = HttpStatus.OK;
		} else {
			httpStatus = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<User>(result, httpStatus);

	}

	
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a user")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		userService.addUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	
	@ApiOperation(value = "List all users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/user", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a user")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		HttpStatus httpStatus;
		if (userService.isUserAvailable(user.getUserid())) {
			userService.addUser(user);
			httpStatus = HttpStatus.OK;
		} else {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(user, httpStatus);
	}

	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a user")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		userService.removeUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	@RequestMapping(value = "search/user/{name}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Users list by name")
	public ResponseEntity<List<User>> getUsersByName(@PathVariable String name) {
		User usersearch = new User();
		List<User> search = new ArrayList<User>();
		HttpStatus httpStatus;

		List<User> users = userService.getAllUsers();

		users.forEach(user -> {
			if (user.getName().equals(name)) {

				usersearch.setName(user.getName());
				usersearch.setAddress(user.getAddress());
				usersearch.setUserid(user.getUserid());
				search.add(usersearch);
			}

		});

		if (search.size() > 0) {
			httpStatus = HttpStatus.OK;
		} else {
			httpStatus = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<List<User>>(search, httpStatus);
	}

}
