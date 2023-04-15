package jinny.springboot.security.jwt.web;

import jinny.springboot.security.jwt.data.dto.SignInResult;
import jinny.springboot.security.jwt.data.dto.SignUpAccountReq;
import jinny.springboot.security.jwt.data.dto.SignUpAccountResult;
import jinny.springboot.security.jwt.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/sign")
@RestController
public class SignController {

	private final SignService signService;

	@PostMapping(path = "/sign-up")
	public SignUpAccountResult signUp(@RequestBody SignUpAccountReq param) {
		return signService.signup(param);
	}

	@PostMapping(path = "/sign-in")
	public SignInResult signIn(@RequestParam String id,
							   @RequestParam String password) {
		return signService.signIn(id, password);
	}
}
