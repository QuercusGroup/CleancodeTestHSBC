package uk.co.hsbc.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.hsbc.library.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
