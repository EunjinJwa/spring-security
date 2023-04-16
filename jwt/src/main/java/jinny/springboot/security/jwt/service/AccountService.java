package jinny.springboot.security.jwt.service;

import jinny.springboot.security.jwt.common.AccountStatus;
import jinny.springboot.security.jwt.data.entity.Account;

import java.util.List;

public interface AccountService {
	Account getAccount(String uid);

	List<Account> getAccounts();

	Account changeAccountStatus(String uid, AccountStatus status);
}
