package hsbc.codetest.librarybooksms.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hsbc.codetest.librarybooksms.data.entity.BookEntity;

@Repository
public interface BookDataService extends CrudRepository<BookEntity, Long>{

}
