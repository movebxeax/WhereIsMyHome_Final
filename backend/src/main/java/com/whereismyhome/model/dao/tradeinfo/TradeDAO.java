package com.whereismyhome.model.dao.tradeinfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.tradeinfo.TradeInfo;
import com.whereismyhome.model.dto.tradeinfo.TradeInfoSpec;

@Mapper
public interface TradeDAO {
	public List<TradeInfo> selectTradeListWithDongCode(Map<String, String> map);
	public TradeInfoSpec selectTradeListWithAptCode(String aptCode);
}
