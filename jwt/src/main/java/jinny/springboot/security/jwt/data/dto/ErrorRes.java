package jinny.springboot.security.jwt.data.dto;

import jinny.springboot.security.jwt.common.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ErrorRes {

	private int httpStatus;
	private ErrorCode errorCode;
	private String message;

}
