package hsbc.codetest.librarybooksms.bss.exception;

public class RecordAlreadyExist extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2800258526892488314L;

	public RecordAlreadyExist(String recordDesc) {
		super(String.format("%s %s", recordDesc, "alreay exists!"));
	}

	
}
