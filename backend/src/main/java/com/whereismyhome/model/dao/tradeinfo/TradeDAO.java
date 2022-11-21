package com.whereismyhome.model.dao.tradeinfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.tradeinfo.DongInfo;
import com.whereismyhome.model.dto.tradeinfo.TradeInfo;
import com.whereismyhome.model.dto.tradeinfo.TradeInfoSpec;

@Mapper
public interface TradeDAO {
	public List<TradeInfo> selectTradeListWithDongCode(Map<String, Object> map);
	public TradeInfoSpec selectTradeListWithAptCode(String aptCode);
	public List<TradeInfo> selectAptListWithCoord(Map<String, Object> map);
	public List<DongInfo> selectDongInfoListWithCoord(Map<String, Object> map);
}
