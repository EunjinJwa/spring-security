package jinny.springboot.security.jwt.service.impl;

import jinny.springboot.security.jwt.common.AccountStatus;
import jinny.springboot.security.jwt.data.entity.Account;
import jinny.springboot.security.jwt.data.repository.AccountRepository;
import jinny.springboot.security.jwt.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	public Account getAccount(String uid) {
		return accountRepository.findByUid(uid);
	}

	@Override
	public List<Account> getAccounts() {
		return accountRepository.findAll();
	}

	@Transactional
	@Override
	public Account changeAccountStatus(String uid, AccountStatus status) {
		Account account = accountRepository.findByUid(uid);
		account.changeStatus(status);
		return account;
	}
}
