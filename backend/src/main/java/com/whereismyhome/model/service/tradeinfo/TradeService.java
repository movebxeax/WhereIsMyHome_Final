package com.whereismyhome.model.service.tradeinfo;

import java.util.List;
import java.util.Map;

import com.whereismyhome.model.dto.tradeinfo.DongInfo;
import com.whereismyhome.model.dto.tradeinfo.GugunInfo;
import com.whereismyhome.model.dto.tradeinfo.TradeInfo;
import com.whereismyhome.model.dto.tradeinfo.TradeInfoSpec;

public interface TradeService {
	public List<TradeInfo> getTradeList(Map<String, Object> map);
	public List<TradeInfo> getAptListWithCoord(Map<String, Object> map);
	public List<DongInfo> getDongInfoListWithCoord(Map<String, Object> map);	
	public List<GugunInfo> getGugunInfoListWithCoord(Map<String, Object> params);
	public TradeInfoSpec getTradeSpecWithAptCode(String aptCode);
	public List<TradeInfo> getTradeListWithCoord(Map<String, Object> params);
}
