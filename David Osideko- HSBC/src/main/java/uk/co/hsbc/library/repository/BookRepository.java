package uk.co.hsbc.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.co.hsbc.library.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

	@Query("SELECT b FROM Book b where b.uuid = :uuid")
	Book findByUuid(@Param("uuid") String uuid);
}
