package com.whereismyhome.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.whereismyhome.config.exception.UnauthorizedErrorException;
import com.whereismyhome.model.dto.tradeinfo.InterestInfo;
import com.whereismyhome.model.service.tradeinfo.InterestService;
import com.whereismyhome.util.ResponseManager;

@Transactional
@RequestMapping("/api/interest")
@SessionAttributes("userid") // is it right?
@RestController
public class InterestController extends ResponseManager {
	
	@Autowired
	InterestService interestService;

	@GetMapping("/{aptCode}")
	protected ResponseEntity<?> getInterestInfoList(@PathVariable String aptCode, Principal principal) throws Exception {
		if(principal == null || principal.getName().equals(""))
			throw new UnauthorizedErrorException();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aptCode", aptCode);
		map.put("userid", principal.getName());
		
		InterestInfo interestInfo = interestService.getInterestInfo(map);
		
		if(interestInfo != null)
			return createResponse(interestInfo);
		else
			return createResponse(HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	@PostMapping("/{aptCode}")
	protected ResponseEntity<?> addInterestInfo(@PathVariable String aptCode, Principal principal) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aptCode", aptCode);
		map.put("userid", principal.getName());
		
		return createResponse(interestService.addInterestInfo(map));
	}
	
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{aptCode}")
	protected ResponseEntity<?> removeInterestInfo(@PathVariable String aptCode, Principal principal) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aptCode", aptCode);
		map.put("userid", principal.getName());
		
		return createResponse(interestService.removeInterestInfo(map));
	}
	
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	@GetMapping
	protected ResponseEntity<?> getInterestInfoList(Principal principal) throws Exception {		
		List<InterestInfo> interestInfo = interestService.getInterestInfoList(principal.getName());
		return createResponse(interestInfo);
	}
}
