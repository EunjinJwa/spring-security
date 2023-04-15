package jinny.springboot.security.jwt.data.repository;

import jinny.springboot.security.jwt.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
