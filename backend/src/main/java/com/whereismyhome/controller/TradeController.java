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

import com.whereismyhome.config.exception.InternalServerErrorException;
import com.whereismyhome.model.service.tradeinfo.TradeService;
import com.whereismyhome.util.ResponseManager;

@RequestMapping("/api/trade")
@RestController
public class TradeController extends ResponseManager{

	@Autowired
	TradeService tradeService;

	@GetMapping("/apt")
	protected ResponseEntity<?> getAptListWithCoord(@RequestParam Map<String, Object> params) {
		try {
			return createResponse(HttpStatus.OK, tradeService.getAptListWithCoord(params));
		} catch(Exception e) {
			throw new InternalServerErrorException("좌표 기준 건물 검색 실패");
		}
	}

	@GetMapping("/dong")
	protected ResponseEntity<?> getDongInfoListWithCoord(@RequestParam Map<String, Object> params) {
		try {
			return createResponse(HttpStatus.OK, tradeService.getDongInfoListWithCoord(params));
		} catch(Exception e) {
			throw new InternalServerErrorException("좌표 기준 법정동 정보 검색 실패");
		}
	}

	@GetMapping("/gugun")
	protected ResponseEntity<?> getGugunInfoListWithCoord(@RequestParam Map<String, Object> params) {
		try {
			return createResponse(HttpStatus.OK, tradeService.getGugunInfoListWithCoord(params));
		} catch(Exception e) { 
			throw new InternalServerErrorException("좌표 기준 행정구 정보 검색 실패");
		}
	}

	@GetMapping("/list")
	protected ResponseEntity<?> getTradeListWithCoord(@RequestParam Map<String, Object> params) {
		try {
			return createResponse(HttpStatus.OK, tradeService.getTradeListWithCoord(params));
		} catch(Exception e) {
			throw new InternalServerErrorException("좌표 기준 거래 정보 검색 실패");
		}
	}

	@GetMapping("/list/{dongCode}")
	protected ResponseEntity<?> getTradeList(@PathVariable String dongCode,
			@RequestParam(required = false) Map<String,String> params)
	{
		try {
			HashMap<String, Object> map = new HashMap<>();
			map.put("params", params);
			map.put("dongcode", dongCode);

			return createResponse(HttpStatus.OK, tradeService.getTradeList(map));
		} catch(Exception e) {
			throw new InternalServerErrorException("거래 정보 검색 실패");
		}
	}

	@GetMapping("/info/{aptCode}")
	protected ResponseEntity<?> getTradeSpecWithAptCode(@PathVariable String aptCode) {
		try {
			return createResponse(HttpStatus.OK, tradeService.getTradeSpecWithAptCode(aptCode));
		} catch(Exception e) {
			throw new InternalServerErrorException("거래 상세 정보 검색 실패");
		}
	}
}
