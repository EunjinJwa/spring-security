package jinny.springboot.security.jwt.common;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AccountRoleType {
	ADMIN("admin", "ROLE_ADMIN"),
	USER("user", "ROLE_USER");

	private String role;
	private String roleType;

	AccountRoleType(String role, String roleType) {
		this.role = role;
		this.roleType = roleType;
	}

	public static AccountRoleType getByRole(String role) {
		return Arrays.stream(AccountRoleType.values()).filter(r -> r.getRole().equals(role)).findFirst().orElse(null);
	}
}
