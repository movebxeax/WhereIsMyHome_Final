package com.whereismyhome.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.whereismyhome.model.service.tradeinfo.CommercialService;
import com.whereismyhome.util.ResponseManager;

@RequestMapping("/api/commercial")
@RestController
public class CommercialController extends ResponseManager {
	@Autowired
	CommercialService commercialService;

	@GetMapping("/{dongCode}")
	protected ResponseEntity<?> getCommercialList(@PathVariable String dongCode) throws Exception {
		return createResponse(commercialService.getCommercialList(dongCode));
	}
}
