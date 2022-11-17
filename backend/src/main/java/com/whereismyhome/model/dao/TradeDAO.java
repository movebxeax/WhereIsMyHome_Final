package com.whereismyhome.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.TradeInfo;

@Mapper
public interface TradeDAO {
	public List<TradeInfo> getTradeInfoList(Map<String,Object> map);
}
