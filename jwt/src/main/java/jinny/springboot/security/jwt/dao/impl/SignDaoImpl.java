package jinny.springboot.security.jwt.dao.impl;

import jinny.springboot.security.jwt.dao.SignDao;
import jinny.springboot.security.jwt.data.dto.SignUpAccountResult;
import jinny.springboot.security.jwt.data.entity.Account;
import jinny.springboot.security.jwt.data.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@RequiredArgsConstructor
@Component
public class SignDaoImpl implements SignDao {

	private final AccountRepository accountRepository;

	@Override
	public SignUpAccountResult create(Account account) {
		Account savedAccount = accountRepository.save(account);
		return SignUpAccountResult.of(savedAccount);
	}
}
