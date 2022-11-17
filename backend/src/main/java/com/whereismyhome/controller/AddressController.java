package com.whereismyhome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyhome.model.dto.DistrictInfo;
import com.whereismyhome.model.service.AddressService;

@RequestMapping("/api/address")
@RestController
public class AddressController {
	
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

	protected ResponseEntity<?> createResponse(List<DistrictInfo> di) {
		if(di != null)
			return ResponseEntity.ok().body(di);
		else
			return ResponseEntity.notFound().build();
	}
}
