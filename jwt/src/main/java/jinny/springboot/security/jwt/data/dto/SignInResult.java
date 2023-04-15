package jinny.springboot.security.jwt.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignInResult {

	private String token;

	public SignInResult(String token) {
		this.token = token;
	}

	public static SignInResult of(String token) {
		return new SignInResult(token);
	}
}
