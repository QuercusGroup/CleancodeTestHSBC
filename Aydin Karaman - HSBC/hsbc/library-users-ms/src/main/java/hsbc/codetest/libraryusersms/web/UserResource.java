package hsbc.codetest.libraryusersms.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hsbc.codetest.libraryusersms.bss.UserService;
import hsbc.codetest.libraryusersms.bss.dto.UserDto;

@RestController
public class UserResource {

	@Autowired
	private UserService userService;

	@PostMapping
	public UserDto saveUser(@Valid @RequestBody UserDto user) {
		return userService.saveUser(user);
	}

	@PutMapping
	public UserDto updateOrCreateUser(@Valid @RequestBody UserDto user) {
		return userService.updateOrCreateUser(user);
	}
	
	@DeleteMapping("/{id}")
	public UserDto deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
	
	@DeleteMapping("/logical/{id}")
	public UserDto deleteUserLogical(@PathVariable Long id) {
		return userService.deleteUserLogical(id);
	}
	
	@GetMapping
	public List<UserDto> getAllRecords() {
		return userService.getAllRecords();
	}
	
	@GetMapping("/{id}")
	public UserDto updateOrCreateUser(@PathVariable Long id) {
		return userService.getById(id);
	}
}
