package uk.co.hsbc.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.hsbc.library.model.Loan;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

	@Query("SELECT l FROM Loan l where l.returnDate IS NULL")
	Iterable<Loan> findCurrentLoans();
}
