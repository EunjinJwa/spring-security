package jinny.springboot.security.jwt.service.impl;

import jinny.springboot.security.jwt.common.AccountRole;
import jinny.springboot.security.jwt.common.AccountStatus;
import jinny.springboot.security.jwt.dao.SignDao;
import jinny.springboot.security.jwt.data.dto.SignUpAccountReq;
import jinny.springboot.security.jwt.data.dto.SignUpAccountResult;
import jinny.springboot.security.jwt.data.entity.Account;
import jinny.springboot.security.jwt.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class SignServiceImpl implements SignService {

	private final SignDao signDao;
	@Override
	public SignUpAccountResult signup(SignUpAccountReq param) {
		Account account = Account.builder()
				.uid(param.getUid())
				.name(param.getName())
				.password(param.getPassword())
				.email(param.getEmail())
				.roles(Collections.singletonList(AccountRole.getByRole(param.getRole()).getRoleType()))
				.status(AccountStatus.ACTIVE.toString())
				.build();

		return signDao.create(account);
	}
}
