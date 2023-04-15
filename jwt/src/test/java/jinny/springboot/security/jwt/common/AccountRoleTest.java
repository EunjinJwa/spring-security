package jinny.springboot.security.jwt.common;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountRoleTest {

	@Test
	public void getAccountRoleTest() {
		String role = "admin";
		AccountRole accountRole = Arrays.stream(AccountRole.values()).filter(r -> r.getRole().equals(role)).findFirst().orElse(null);

		System.out.println(accountRole);
	}

}