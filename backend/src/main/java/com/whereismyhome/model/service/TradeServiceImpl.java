package com.whereismyhome.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismyhome.model.dao.TradeDAO;
import com.whereismyhome.model.dto.TradeInfo;

@Service
public class TradeServiceImpl implements TradeService {
	TradeDAO tradeDao;
	
	@Autowired
	public TradeServiceImpl(TradeDAO tradeDao) {
		this.tradeDao = tradeDao;
	}
	
	public List<TradeInfo> getTradeList(Map<String,Object> map)
	{
		return tradeDao.getTradeInfoList(map);
	}
}
