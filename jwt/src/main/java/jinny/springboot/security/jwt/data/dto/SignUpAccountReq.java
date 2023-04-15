package jinny.springboot.security.jwt.data.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpAccountReq {
	@ApiParam(value = "ID", required = true)
	private String uid;
	@ApiParam(value = "password", required = true)
	private String password;
	@ApiParam(value = "이름", required = true)
	private String name;
	@ApiParam(value = "email", required = true)
	private String email;
	@ApiParam(value = "권한", required = true)
	private String role;
}
