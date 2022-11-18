package com.whereismyhome.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.whereismyhome.model.dto.tradeinfo.InterestInfo;
import com.whereismyhome.model.service.tradeinfo.InterestService;
import com.whereismyhome.util.ResponseManager;

@RequestMapping("/api/interest")
@SessionAttributes("userid") // is it right?
@RestController
public class InterestController extends ResponseManager {
	
	@Autowired
	InterestService interestService;	

	@PostMapping
	protected ResponseEntity<?> addInterestInfo(@RequestBody InterestInfo interestInfo, HttpSession session) throws Exception {
		String userid = (String) session.getAttribute("userid");
		if(userid == null)
			return createResponse(false);

		interestInfo.setUserid(userid);
		return createResponse(interestService.addInterestInfo(interestInfo));
	}
	
	@GetMapping
	protected ResponseEntity<?> getInterestInfo(HttpSession session) throws Exception {
		String userid = (String) session.getAttribute("userid");
		if(userid == null)
			return null;
		
		return createResponse(interestService.getInterestInfo(userid));
	}
}
