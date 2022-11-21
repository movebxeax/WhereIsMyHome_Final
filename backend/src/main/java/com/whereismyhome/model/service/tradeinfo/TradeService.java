package com.whereismyhome.model.service.tradeinfo;

import java.util.List;
import java.util.Map;

import com.whereismyhome.model.dto.tradeinfo.DongInfo;
import com.whereismyhome.model.dto.tradeinfo.TradeInfo;
import com.whereismyhome.model.dto.tradeinfo.TradeInfoSpec;

public interface TradeService {
	public List<TradeInfo> getTradeList(Map<String, Object> map);
	public List<TradeInfo> getAptListWithCoord(Map<String, Object> map);
	public List<DongInfo> getDongInfoListWithCoord(Map<String, Object> map);	
	public TradeInfoSpec getTradeSpecWithAptCode(String aptCode);
}
