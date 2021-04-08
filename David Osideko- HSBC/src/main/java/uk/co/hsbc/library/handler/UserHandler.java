package uk.co.hsbc.library.handler;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.hsbc.library.exception.InvalidUserIdException;
import uk.co.hsbc.library.model.User;
import uk.co.hsbc.library.repository.UserRepository;
import uk.co.hsbc.library.service.model.UserInfo;

import java.time.Instant;
import java.util.List;

@Component
public class UserHandler {

	@Autowired
	private UserRepository repository;

	public User addUser(UserInfo userModel) {
		User createdUser = new User();
		createdUser.setSubscriptionDate(Instant.now());
		createdUser.setEmail(userModel.getEmail());
		update(userModel, createdUser);
		return repository.save(createdUser);
	}

	public List<User> getAllUsers() {
		List<User> collection = Lists.newArrayList();
		repository.findAll().forEach(collection::add);
		return collection;
	}

	public User getUser(long id) throws InvalidUserIdException {
		return repository.findById(id).orElseThrow(InvalidUserIdException::new);
	}

	public User updateUser(long id, UserInfo userModel) throws InvalidUserIdException {
		User user = getUser(id);
		update(userModel, user);
		return repository.save(user);
	}

	public void deleteUser(long id) throws InvalidUserIdException {
		User user = getUser(id);
		repository.delete(user);
	}

	private void update(UserInfo source, User target) {
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setGender(source.getGender());
		target.setStaff(source.isStaff());
		target.setLibrarian(source.isLibrarian());
	}
}
