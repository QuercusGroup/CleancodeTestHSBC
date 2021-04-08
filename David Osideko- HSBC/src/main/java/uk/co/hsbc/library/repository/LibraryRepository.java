package uk.co.hsbc.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.hsbc.library.model.Library;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Long> {

	@Query("SELECT l FROM Library l where l.isSelf = 'true'")
	Library findLibrary();
}
