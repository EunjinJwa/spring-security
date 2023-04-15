package jinny.springboot.security.jwt.dao;

import jinny.springboot.security.jwt.data.dto.SignUpAccountResult;
import jinny.springboot.security.jwt.data.entity.Account;

public interface SignDao {

	SignUpAccountResult create(Account account);
}
