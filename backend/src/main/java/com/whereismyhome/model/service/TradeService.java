package com.whereismyhome.model.service;

import java.util.List;
import java.util.Map;

import com.whereismyhome.model.dto.TradeInfo;

public interface TradeService {
	public List<TradeInfo> getTradeList(Map<String, Object> map);
}
