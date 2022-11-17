package com.whereismyhome.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismyhome.model.dao.TradeDAO;
import com.whereismyhome.model.dto.tradeinfo.TradeInfo;
import com.whereismyhome.model.dto.tradeinfo.TradeInfoSpec;

@Service
public class TradeServiceImpl implements TradeService {
	TradeDAO tradeDao;
	
	@Autowired
	public TradeServiceImpl(TradeDAO tradeDao) {
		this.tradeDao = tradeDao;
	}
	
	public List<TradeInfo> getTradeList(String dongCode)
	{
		return tradeDao.selectTradeListWithDongCode(dongCode);
	}

	@Override
	public TradeInfoSpec getTradeSpecWithAptCode(String aptCode) {
		return tradeDao.selectTradeListWithAptCode(aptCode);
	}
}
