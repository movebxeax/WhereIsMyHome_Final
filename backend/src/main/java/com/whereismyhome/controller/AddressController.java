package com.whereismyhome.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyhome.model.service.tradeinfo.AddressService;
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
		return response(addressService.getSidoList());
	}
	
	@GetMapping("/gugun/{sidoCode}")
	protected ResponseEntity<?> gugunList(@PathVariable String sidoCode) {
		return response(addressService.getGugunList(sidoCode));		
	}
	
	@GetMapping("/dong/{gugunCode}")
	protected ResponseEntity<?> dongList(@PathVariable String gugunCode) {
		return response(addressService.getDongList(gugunCode));
	}
	
	@GetMapping
	protected ResponseEntity<?> searchBaseAddressListWithKeyword(@RequestParam String keyword) {
		return response(addressService.searchBaseAddressListWithKeyword(keyword));
	}
	
	private ResponseEntity<?> response(Object res) {
		if(res != null)
			return createResponse(HttpStatus.OK, res);
		else
			return createResponse(HttpStatus.NOT_FOUND);
	}
}
