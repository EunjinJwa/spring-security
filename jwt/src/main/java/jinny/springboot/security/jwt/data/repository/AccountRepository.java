package jinny.springboot.security.jwt.data.repository;

import jinny.springboot.security.jwt.common.AccountStatus;
import jinny.springboot.security.jwt.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByUid(String id);
}
