package com.whereismyhome.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyhome.model.service.TradeService;

@RequestMapping("/api/trade")
@RestController
public class TradeController {

	@Autowired
	TradeService tradeService;

	@GetMapping
	protected ResponseEntity<?> getTradeList(@RequestParam String dongCode
			, @RequestParam(required = false, defaultValue = "0") int dealYear
			, @RequestParam(required = false) String aptName) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dongCode", dongCode);
		map.put("dealYear", dealYear);
		map.put("aptName", aptName);
		
		return createResponse(tradeService.getTradeList(map));
	}

	protected ResponseEntity<?> createResponse(Object param)
	{
		if(param != null)
			return ResponseEntity.ok().body(param);
		else
			return ResponseEntity.notFound().build();
	}
}
