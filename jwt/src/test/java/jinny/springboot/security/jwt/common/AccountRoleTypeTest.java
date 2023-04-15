package jinny.springboot.security.jwt.common;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class AccountRoleTypeTest {

	@Test
	public void getAccountRoleTest() {
		String role = "admin";
		AccountRoleType accountRoleType = Arrays.stream(AccountRoleType.values()).filter(r -> r.getRole().equals(role)).findFirst().orElse(null);

		System.out.println(accountRoleType);
	}

}