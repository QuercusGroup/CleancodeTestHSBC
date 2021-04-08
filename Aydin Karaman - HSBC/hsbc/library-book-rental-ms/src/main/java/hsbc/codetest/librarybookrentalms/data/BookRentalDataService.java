package hsbc.codetest.librarybookrentalms.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hsbc.codetest.librarybookrentalms.data.entity.BookRentalEntity;

@Repository
public interface BookRentalDataService extends CrudRepository<BookRentalEntity, Long> {
	List<BookRentalEntity> findByUserId(Long userId);
	List<BookRentalEntity> findByBookId(Long bookId);
	
	boolean existsByBookIdAndReturnDateIsNull(Long bookId);
}
