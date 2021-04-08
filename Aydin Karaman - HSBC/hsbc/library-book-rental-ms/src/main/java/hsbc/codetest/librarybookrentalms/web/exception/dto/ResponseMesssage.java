package hsbc.codetest.librarybookrentalms.web.exception.dto;

public class ResponseMesssage {
	private int status;
	private String errorMessage;

	public ResponseMesssage(int status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public int getStatus() {
		return status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
