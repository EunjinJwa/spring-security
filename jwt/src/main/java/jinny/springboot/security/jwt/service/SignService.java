package jinny.springboot.security.jwt.service;

import jinny.springboot.security.jwt.data.dto.SignInResult;
import jinny.springboot.security.jwt.data.dto.SignUpAccountReq;
import jinny.springboot.security.jwt.data.dto.SignUpAccountResult;

public interface SignService {


	SignUpAccountResult signup(SignUpAccountReq param);

	SignInResult signIn(String id, String password);
}
