package hsbc.codetest.libraryusersms.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hsbc.codetest.libraryusersms.data.entity.UserEntity;

@Repository
public interface UserDataService extends CrudRepository<UserEntity, Long>{

}
