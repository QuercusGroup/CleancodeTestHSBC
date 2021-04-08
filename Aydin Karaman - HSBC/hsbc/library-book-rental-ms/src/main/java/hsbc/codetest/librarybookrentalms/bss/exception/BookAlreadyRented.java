package hsbc.codetest.librarybookrentalms.bss.exception;

public class BookAlreadyRented extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2800258526892488314L;

	public BookAlreadyRented(String recordDesc) {
		super(String.format("Book %s already rented", recordDesc));
	}

	
}
