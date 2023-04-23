package jinny.springboot.security.jwt.service.impl;

import jinny.springboot.security.jwt.common.AccountRoleType;
import jinny.springboot.security.jwt.common.AccountStatus;
import jinny.springboot.security.jwt.common.ErrorCode;
import jinny.springboot.security.jwt.config.Security.JwtTokenProvider;
import jinny.springboot.security.jwt.data.dto.SignInResult;
import jinny.springboot.security.jwt.data.dto.SignUpAccountReq;
import jinny.springboot.security.jwt.data.dto.SignUpAccountResult;
import jinny.springboot.security.jwt.data.entity.Account;
import jinny.springboot.security.jwt.data.entity.AccountRole;
import jinny.springboot.security.jwt.data.repository.AccountRepository;
import jinny.springboot.security.jwt.handler.exception.CustomErrorException;
import jinny.springboot.security.jwt.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SignServiceImpl implements SignService {
	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	@Override
	public SignUpAccountResult signup(SignUpAccountReq param) {
		Account account;
		account = Account.builder()
				.uid(param.getUid())
				.name(param.getName())
				.password(passwordEncoder.encode(param.getPassword()))
				.email(param.getEmail())
				.roles(Collections.singletonList(AccountRole.builder().role(AccountRoleType.getByRole(param.getRole()).getRoleType()).build()))
				.status(AccountStatus.ACTIVE.toString())
				.build();

		List<AccountRole> accountRoles = Collections.singletonList(AccountRole.builder()
				.role(AccountRoleType.getByRole(param.getRole()).getRoleType())
				.account(account)
				.build());
		account.setRoles(accountRoles);

		Account savedAccount = accountRepository.save(account);
		return SignUpAccountResult.of(savedAccount);
	}

	@Override
	public SignInResult signIn(String id, String password) {
		// 유저 정보 확인
		Account account = accountRepository.findByUid(id);
		if (account == null) {
			throw new CustomErrorException(ErrorCode.USER_NOT_FOUND, id);
		}

		// 유저의 password와 parameter의 password가 동일한지 확인
		boolean matches = passwordEncoder.matches(password, account.getPassword());
		if (!matches) {
			throw new CustomErrorException(ErrorCode.INVALID_PASSWORD, "Password is not invalid.");
		}

		// 토큰 생성
		String token = jwtTokenProvider.createToken(id, account.getRoles());
		return SignInResult.of(token);

	}
}
