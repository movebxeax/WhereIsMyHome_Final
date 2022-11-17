package com.whereismyhome.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyhome.model.service.AddressService;
import com.whereismyhome.util.ResponseManager;

@RequestMapping("/api/address")
@RestController
public class AddressController extends ResponseManager {
	
	// !! todo !!
	// change urls as restful style

	@Autowired
	private AddressService addressService;

	@GetMapping("/sido")
	protected ResponseEntity<?> sidoList() {
		//return createResponse("test");
		return createResponse(addressService.getSidoList());
	}
	
	@GetMapping("/gugun/{sidoCode}")
	protected ResponseEntity<?> gugunList(@PathVariable String sidoCode) {
		return createResponse(addressService.getGugunList(sidoCode));
		
	}
	
	@GetMapping("/dong/{gugunCode}")
	protected ResponseEntity<?> dongList(@PathVariable String gugunCode) {
		return createResponse(addressService.getDongList(gugunCode));
	}
}
