package jinny.springboot.security.jwt.handler;

import jinny.springboot.security.jwt.common.ErrorCode;
import jinny.springboot.security.jwt.data.dto.ErrorRes;
import jinny.springboot.security.jwt.handler.exception.CustomErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//CustomException을 상속받은 클래스가 예외를 발생 시킬 시, Catch하여 ErrorResponse를 반환한다.
	@ExceptionHandler(CustomErrorException.class)
	protected ResponseEntity<ErrorRes> handleCustomException(CustomErrorException e) {
		logger.error("handleCustomException", e);

		ErrorRes response = ErrorRes.builder()
				.errorCode(e.getErrorCode())
				.httpStatus(e.getHttpStatus().value())
				.message(e.getMessage())
				.build();

		return new ResponseEntity<>(response, e.getHttpStatus());
	}

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<ErrorRes> handleRuntimeException(RuntimeException e) {
		logger.error("handleCustomException", e);

		ErrorRes response = ErrorRes.builder()
				.errorCode(ErrorCode.INTERNAL_ERROR)
				.httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message(e.getMessage())
				.build();

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
