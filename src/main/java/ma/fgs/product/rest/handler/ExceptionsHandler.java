package ma.fgs.product.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ma.fgs.product.service.exception.BadCredentialsException;
import ma.fgs.product.service.exception.NotFoundException;

@ControllerAdvice
public class ExceptionsHandler {

	@ResponseBody
	@ExceptionHandler(value = BadCredentialsException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseError handleBadCredentials(BadCredentialsException ex) {
		return new ResponseError(ex.getCode(), ex.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(value = NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseError handleNotFound(NotFoundException ex) {
		return new ResponseError(ex.getCode(), ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseError handleAllException(Exception ex) {
		return new ResponseError("SERVER.EXCEPTION" , ex.getMessage());
	}

	static class ResponseError {
		private String code;
		private String message;

		public ResponseError() {
			super();
		}

		public ResponseError(String code, String message) {
			super();
			this.code = code;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
