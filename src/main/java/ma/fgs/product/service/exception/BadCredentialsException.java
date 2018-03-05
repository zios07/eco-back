package ma.fgs.product.service.exception;

public class BadCredentialsException extends Exception {

	private static final long serialVersionUID = 8323311965476082126L;

	private String code;

	public BadCredentialsException() {
		
	}
	
	public BadCredentialsException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
