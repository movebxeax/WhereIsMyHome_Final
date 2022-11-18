package com.whereismyhome.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.whereismyhome.config.security.Role;
import com.whereismyhome.model.dto.user.UserInfo;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {
	private PasswordEncoder passwordEncoder;

	@Autowired
	//private MemberRepository memberRepository;
	private UserService userService;
	
	@Autowired
	public JwtUserDetailsService(UserService userService, @Lazy PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		UserInfo userInfo = userService.findUserByUserId(userid);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));

		return new User(userInfo.getUserid(), userInfo.getPassword(), grantedAuthorities);
	}

	public UserInfo authenticateByUseridAndPassword(String userid, String password) {
		UserInfo userInfo = userService.findUserByUserId(userid);
		log.info(userid);
		log.info(password);
		
		if(!passwordEncoder.matches(password, userInfo.getPassword())) {
			throw new BadCredentialsException("Password not matched");
		}

		return userInfo;
	}

}
