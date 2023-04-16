package jinny.springboot.security.jwt.web;

import jinny.springboot.security.jwt.common.AccountStatus;
import jinny.springboot.security.jwt.data.entity.Account;
import jinny.springboot.security.jwt.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@RestController
public class AccountController {

	private final AccountService accountService;

	@GetMapping
	public List<Account> getAllAcounts() {
		return accountService.getAccounts();
	}

	@GetMapping(path = "/{uid}")
	public Account getAccount(@PathVariable String uid) {
		return accountService.getAccount(uid);
	}

	@PutMapping(path = "/status/{uid}")
	public Account changeAccountStatus(@PathVariable String uid,
									   @RequestParam AccountStatus status) {
		return accountService.changeAccountStatus(uid, status);
	}


}
