package com.whereismyhome.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
		return createResponse(createJwtResponse(userInfo.getUserid(), userInfo.getUsername()));
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
			return createResponse(createJwtResponse(requestBody.getUserid(), userDetails.getUsername()));
		else
			return createResponse(HttpStatus.UNAUTHORIZED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
	@GetMapping("/info")
	protected ResponseEntity<?> info(Principal principal) {
		UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(principal.getName());

		if(userDetails != null)
			return createResponse(userService.getUserInfo(principal.getName()));
		else
			return createResponse(HttpStatus.UNAUTHORIZED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
	@PostMapping("/modify")
	protected ResponseEntity<?> modify(@RequestBody UserInfoDetail userInfo, Principal principal) {
		UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(principal.getName());
		UserInfoDetail checkExist = userService.getUserInfo(principal.getName());
		
		if(checkExist != null)
		{
			if(userDetails != null)
				return createResponse(userService.modifyUserInfo(userInfo));
			else
				return createResponse(HttpStatus.UNAUTHORIZED);
		}
		else
			return createResponse(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/delete")
	protected ResponseEntity<?> delete(Principal principal) {
		UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(principal.getName());		
		UserInfoDetail checkExist = userService.getUserInfo(principal.getName());
		
		if(checkExist != null)
		{
			if(userDetails != null)
				return createResponse(userService.deleteUserInfo(principal.getName()));
			else
				return createResponse(HttpStatus.UNAUTHORIZED);
		}
		else
			return createResponse(HttpStatus.NOT_FOUND);
	}

	private JwtResponse createJwtResponse(String userid, String username) {
		final String accessToken = jwtTokenUtil.generateAccessToken(userid);
		final String refreshToken = jwtTokenUtil.generateRefreshToken(userid);
		return new JwtResponse(userid, username, accessToken, refreshToken);
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
	private String username;
	private String accessToken;
	private String refreshToken;
}

@Data
@AllArgsConstructor
class JwtRefreshRequest {
	private String refreshToken;
	private String userid;
}