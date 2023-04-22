package jinny.springboot.security.jwt.config.Security;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final JwtTokenProvider jwtTokenProvider;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		// 토큰 확인
		String token = jwtTokenProvider.resolveToken(httpServletRequest);
		if (token != null && jwtTokenProvider.validateToken(token)) {
			String uid = jwtTokenProvider.getUserIdByToken(token);
			logger.info("uid: {} , authentication : {}", uid, SecurityContextHolder.getContext().getAuthentication());
			if (uid != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				// 토큰의 권한을 SecurityContextHolder에 추가
				UsernamePasswordAuthenticationToken authentication = getAuthentication(uid);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);

			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);		// servlet 실행. 이후 로직은 서블릿 실행 후 실행됨.
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String uid) {
		logger.info("getAuthentication");
		UserDetails userDetails = userDetailsService.loadUserByUsername(uid);
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}


}
