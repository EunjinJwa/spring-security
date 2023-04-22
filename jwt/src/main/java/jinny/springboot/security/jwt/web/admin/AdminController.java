package jinny.springboot.security.jwt.web.admin;

import jinny.springboot.security.jwt.common.AccountStatus;
import jinny.springboot.security.jwt.data.entity.Account;
import jinny.springboot.security.jwt.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminController {

	private final AccountService accountService;

	@GetMapping("/accounts")
	public List<Account> getAllAcounts() {
		return accountService.getAccounts();
	}

	@PutMapping(path = "account/{uid}/status")
	public Account changeAccountStatus(@PathVariable String uid,
									   @RequestParam AccountStatus status) {
		return accountService.changeAccountStatus(uid, status);
	}
}
