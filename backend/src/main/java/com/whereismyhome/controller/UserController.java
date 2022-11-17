package com.whereismyhome.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyhome.model.dto.UserInfo;
import com.whereismyhome.model.service.UserService;

@RequestMapping("/api/user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	protected ResponseEntity<?> login(HttpSession session, UserInfo userInfo) {
		// !! todo !!
		// use spring securities...
		
		// convert password string
		userInfo.setPassword(createPassword(userInfo.getPassword()));
		UserInfo info = userService.login(userInfo);

		if(info != null)
		{
			session.setAttribute("userid", info.getUserid());
			session.setAttribute("username", info.getUsername());

//			Cookie useridCookie = new Cookie("userid", (String) session.getAttribute("userid"));
//			useridCookie.setPath("/");
//			response.addCookie(useridCookie);
//
//			Cookie usernameCookie = new Cookie("username", (String) session.getAttribute("username"));
//			usernameCookie.setPath("/");
//			response.addCookie(usernameCookie);

			return createResponse(HttpStatus.OK, "");
		}

		return createResponse(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/logout")
	protected ResponseEntity<?> logout(HttpSession session) throws Exception {
		if(session.getAttribute("userid") != null || session.getAttribute("username") != null)
			session.invalidate();

//		Cookie useridCookie = new Cookie("userid", "");
//		useridCookie.setPath("/");
//		useridCookie.setMaxAge(0);
//		response.addCookie(useridCookie);
//
//		Cookie usernameCookie = new Cookie("username", "");
//		usernameCookie.setPath("/");
//		usernameCookie.setMaxAge(0);
//		response.addCookie(usernameCookie);

		return createResponse(HttpStatus.OK);
	}

	@PostMapping("/signup")
	protected ResponseEntity<?> signup(UserInfo userInfo) {
		
		// !! todo !!
		// use spring securities...
		
		// convert password string
		userInfo.setPassword(createPassword(userInfo.getPassword()));
		
		if(!userInfo.getPassword().equals("") && userService.signup(userInfo))
			return createResponse(HttpStatus.OK);
		else
			return createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/{userid}")
	protected ResponseEntity<?> modifyUserInfo(@PathVariable String userid, UserInfo userInfo) {
		// !! todo !!
		// check if user exists..
		
		if(!userInfo.getPassword().equals(""))
		{
			// convert password string
			userInfo.setPassword(createPassword(userInfo.getPassword()));
			
			if(userInfo.getPassword().equals(""))
				return createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(userService.modifyUserInfo(userInfo))
			return createResponse(HttpStatus.OK);
		else
			return createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{userid}")
	protected ResponseEntity<?> getUserInfo(@PathVariable String userid, HttpSession session) {
		System.out.println(session.getAttribute("userid"));
		if(session.getAttribute("userid").equals(userid))
			return createResponse(HttpStatus.OK, userService.getUserInfo(userid));
		else
			return createResponse(HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping("/{userid}")
	protected ResponseEntity<?> deleteUserInfo(@PathVariable String userid, HttpSession session) {
		if(session.getAttribute("userid").equals(userid))
		{
			boolean res = userService.deleteUserInfo(userid);
			if(res)
			{
				session.invalidate();
				return createResponse(HttpStatus.NO_CONTENT);
			}
			else
			{
				return createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
			return createResponse(HttpStatus.UNAUTHORIZED);
	}
	
	protected ResponseEntity<?> createResponse(HttpStatus status, Object... response)
	{
		if(status == HttpStatus.OK)
		{
			return ResponseEntity.ok().body(response);
		}
		else
		{
			return ResponseEntity.status(status).build();
		}
	}
	
	private String createPassword(String password)
	{
		MessageDigest passwd_md5;
		StringBuffer sb = new StringBuffer();
		
		try {
			passwd_md5 = MessageDigest.getInstance("MD5");
			passwd_md5.update(password.getBytes());
			byte byteData[] = passwd_md5.digest();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			return "";
		}		
		return sb.toString();
	}
}
