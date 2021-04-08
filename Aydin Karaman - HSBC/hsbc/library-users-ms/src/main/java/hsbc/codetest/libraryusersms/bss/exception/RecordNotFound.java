package hsbc.codetest.libraryusersms.bss.exception;

public class RecordNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2800258526892488314L;

	public RecordNotFound(String recordDesc) {
		super(String.format("%s %s", recordDesc, "Not Found!"));
	}

	
}
