package jinny.springboot.security.jwt.config.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	private String secretKey = "kassy@secret#key";
	private final long tokenValidMillisecond = 60 * 60 * 1000;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
	}

	public String createToken(String uid, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(uid);
		claims.put("roles", roles);
		Date now = new Date();

		String token = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + tokenValidMillisecond))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
		return token;
	}

	public String resolveToken(HttpServletRequest httpServletRequest) {
		String authorization = httpServletRequest.getHeader("Authorization");
		if (authorization != null && authorization.startsWith("Bearer")) {
			return authorization.substring(7);
		}
		return null;
	}

	public boolean validateToken(String token) {
		// 토큰 기간 체크
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		return !claimsJws.getBody().getExpiration().before(new Date());
	}


	public String getUserIdByToken(String token) {
		return parseTokenClaims(token).getBody().getSubject();

	}

	private Jws<Claims> parseTokenClaims(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
	}

}
