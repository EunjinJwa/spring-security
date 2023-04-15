package jinny.springboot.security.jwt.web;

import jinny.springboot.security.jwt.common.AccountRole;
import jinny.springboot.security.jwt.data.dto.SignUpAccountReq;
import jinny.springboot.security.jwt.data.dto.SignUpAccountResult;
import jinny.springboot.security.jwt.data.entity.Account;
import jinny.springboot.security.jwt.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/sign")
@RestController
public class SignController {

	private final SignService signService;

	@PostMapping(path = "/sign-up")
	public SignUpAccountResult signUp(@RequestBody SignUpAccountReq param) {
		return signService.signup(param);
	}
}
