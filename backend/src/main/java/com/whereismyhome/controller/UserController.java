package com.whereismyhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyhome.config.security.JwtTokenUtil;
import com.whereismyhome.model.dto.user.UserInfo;
import com.whereismyhome.model.dto.user.UserInfoDetail;
import com.whereismyhome.model.service.user.JwtUserDetailsService;
import com.whereismyhome.model.service.user.UserService;
import com.whereismyhome.util.ResponseManager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController extends ResponseManager{

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	private final PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	protected ResponseEntity<?> login(@RequestBody UserInfo userInfoFromClient) {
		if(!StringUtils.hasLength(userInfoFromClient.getUserid()) || !StringUtils.hasLength(userInfoFromClient.getPassword()))
			return createResponse(HttpStatus.FORBIDDEN);

		final UserInfo userInfo = jwtUserDetailsService.authenticateByUseridAndPassword(userInfoFromClient.getUserid(), userInfoFromClient.getPassword());	
		return createResponse(createJwtResponse(userInfo.getUserid()));
	}

	@PostMapping("/signup")
	protected ResponseEntity<?> signup(@RequestBody UserInfoDetail userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		boolean res = userService.signup(userInfo);

		if(res) {
			userInfo.setPassword("");
			return createResponse(HttpStatus.OK, userInfo);
		}
		else
			return createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/refresh")
	protected ResponseEntity<?> refresh(@RequestBody JwtRefreshRequest requestBody) {
		UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(requestBody.getUserid());
		boolean res = jwtTokenUtil.validateToken(requestBody.getRefreshToken(), userDetails);

		if(res)
			return createResponse(createJwtResponse(requestBody.getUserid()));
		else
			return createResponse(HttpStatus.UNAUTHORIZED);
	}

	private JwtResponse createJwtResponse(String userid) {
		final String accessToken = jwtTokenUtil.generateAccessToken(userid);
		final String refreshToken = jwtTokenUtil.generateRefreshToken(userid);
		return new JwtResponse(userid, accessToken, refreshToken);
	}
}

@Data
class JwtRequest {
	private String userid;
	private String password;
}

@Data
@AllArgsConstructor
class JwtResponse {
	private String userid;
	private String accessToken;
	private String refreshToken;
}

@Data
@AllArgsConstructor
class JwtRefreshRequest {
	private String refreshToken;
	private String userid;
}