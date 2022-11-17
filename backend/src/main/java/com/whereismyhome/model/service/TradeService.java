package com.whereismyhome.model.service;

import java.util.List;
import java.util.Map;

import com.whereismyhome.model.dto.tradeinfo.TradeInfo;
import com.whereismyhome.model.dto.tradeinfo.TradeInfoSpec;

public interface TradeService {
	public List<TradeInfo> getTradeList(String dongCode);
	public TradeInfoSpec getTradeSpecWithAptCode(String aptCode);
}
