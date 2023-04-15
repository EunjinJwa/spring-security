package jinny.springboot.security.jwt.common;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AccountRole {
	ADMIN("admin", "ROLE_ADMIN"),
	USER("user", "ROLE_USER");

	private String role;
	private String roleType;

	AccountRole(String role, String roleType) {
		this.role = role;
		this.roleType = roleType;
	}

	public static AccountRole getByRole(String role) {
		return Arrays.stream(AccountRole.values()).filter(r -> r.getRole().equals(role)).findFirst().orElse(null);
	}
}
