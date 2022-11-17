package com.whereismyhome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyhome.model.dto.CommercialInfo;
import com.whereismyhome.model.service.CommercialService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/commercial")
@RestController
public class CommercialController{
	@Autowired
	CommercialService commercialService;

	@GetMapping("/{dongCode}")
	protected ResponseEntity<?> getCommercialList(@PathVariable String dongCode) throws Exception {
		return createResponse(commercialService.getCommercialList(dongCode));
	}
	
	protected ResponseEntity<?> createResponse(List<CommercialInfo> ci) {
		if(ci != null)
			return ResponseEntity.ok().body(ci);
		else
			return ResponseEntity.notFound().build();
	}
}
