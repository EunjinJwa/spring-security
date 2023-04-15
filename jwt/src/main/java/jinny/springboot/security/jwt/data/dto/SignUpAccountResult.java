package jinny.springboot.security.jwt.data.dto;

import jinny.springboot.security.jwt.data.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpAccountResult {
	private boolean isSuccess;
	private long id;
	private String uid;
	private String name;

	public SignUpAccountResult(boolean isSuccess, long id, String uid, String name) {
		this.isSuccess = isSuccess;
		this.id = id;
		this.uid = uid;
		this.name = name;
	}

	public static SignUpAccountResult of(Account savedAccount) {
		return new SignUpAccountResult(true, savedAccount.getId(), savedAccount.getUid(), savedAccount.getName());
	}
}
