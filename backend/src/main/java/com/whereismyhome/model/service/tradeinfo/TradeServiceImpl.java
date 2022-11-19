package com.whereismyhome.model.service.tradeinfo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismyhome.model.dao.tradeinfo.TradeDAO;
import com.whereismyhome.model.dto.tradeinfo.TradeInfo;
import com.whereismyhome.model.dto.tradeinfo.TradeInfoSpec;

@Service
public class TradeServiceImpl implements TradeService {
	TradeDAO tradeDao;
	
	@Autowired
	public TradeServiceImpl(TradeDAO tradeDao) {
		this.tradeDao = tradeDao;
	}
	
	public List<TradeInfo> getTradeList(Map<String, String> map) {
		return tradeDao.selectTradeListWithDongCode(map);
	}

	@Override
	public TradeInfoSpec getTradeSpecWithAptCode(String aptCode) {
		return tradeDao.selectTradeListWithAptCode(aptCode);
	}
}