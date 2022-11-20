package com.whereismyhome.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyhome.model.service.tradeinfo.TradeService;
import com.whereismyhome.util.ResponseManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/trade")
@RestController
public class TradeController extends ResponseManager{

	@Autowired
	TradeService tradeService;

//	@GetMapping
//	protected ResponseEntity<?> getTradeList(@RequestParam String dongCode
//			, @RequestParam(required = false, defaultValue = "0") int dealYear
//			, @RequestParam(required = false) String aptName) {
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("dongCode", dongCode);
//		map.put("dealYear", dealYear);
//		map.put("aptName", aptName);
//		
//		return createResponse(tradeService.getTradeList(map));
//	}
	
	@GetMapping("/list/{dongCode}")
	protected ResponseEntity<?> getTradeList(@PathVariable String dongCode,
			@RequestParam(required = false) Map<String,String> params)
	{
		HashMap<String, Object> map = new HashMap<>();
		map.put("params", params);
		map.put("dongcode", dongCode);
		
		log.warn(map.toString());
		
		return createResponse(HttpStatus.OK, tradeService.getTradeList(map));
	}
	
	@GetMapping("/info/{aptCode}")
	protected ResponseEntity<?> getTradeSpecWithAptCode(@PathVariable String aptCode) {
		return createResponse(HttpStatus.OK, tradeService.getTradeSpecWithAptCode(aptCode));
	}
}
