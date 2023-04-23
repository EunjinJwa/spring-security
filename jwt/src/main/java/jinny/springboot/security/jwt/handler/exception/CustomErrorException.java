package jinny.springboot.security.jwt.handler.exception;

import jinny.springboot.security.jwt.common.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomErrorException extends RuntimeException {

	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private ErrorCode errorCode;

	public CustomErrorException(String message) {
		super(message);
	}

	public CustomErrorException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
}
