package jinny.springboot.security.jwt.web;

import com.google.gson.Gson;
import jinny.springboot.security.jwt.data.entity.Account;
import jinny.springboot.security.jwt.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@RestController
public class AccountController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final AccountService accountService;

	@GetMapping(path = "/{uid}")
	public Account getAccount(@PathVariable String uid, HttpServletRequest request) {
		return accountService.getAccount(uid);
	}

}
