package com.whereismyhome.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.whereismyhome.model.dto.user.UserInfo;
import com.whereismyhome.model.service.user.UserService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userid = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserInfo userInfo = userService.login(new UserInfo(userid));

		if(passwordEncoder.matches(userInfo.getPassword(), password)) {
			throw new BadCredentialsException("UnAuthorized");
		}

		return new UsernamePasswordAuthenticationToken(userid, password);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
