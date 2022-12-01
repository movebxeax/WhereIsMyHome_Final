package com.whereismyhome.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyhome.config.exception.InternalServerErrorException;
import com.whereismyhome.model.service.tradeinfo.AddressService;
import com.whereismyhome.util.ResponseManager;

@RequestMapping("/api/address")
@RestController
public class AddressController extends ResponseManager {
	@Autowired
	private AddressService addressService;

	@GetMapping
	protected ResponseEntity<?> searchBaseAddressListWithKeyword(@RequestParam String keyword) {
		try {
			return createResponse(addressService.searchBaseAddressListWithKeyword(keyword));
		} catch(Exception e) {
			throw new InternalServerErrorException("주소 검색 실패");
		}
	}
}
