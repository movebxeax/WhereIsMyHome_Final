package com.whereismyhome.model.service.tradeinfo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismyhome.model.dao.tradeinfo.TradeDAO;
import com.whereismyhome.model.dto.tradeinfo.DongInfo;
import com.whereismyhome.model.dto.tradeinfo.GugunInfo;
import com.whereismyhome.model.dto.tradeinfo.TradeInfo;
import com.whereismyhome.model.dto.tradeinfo.TradeInfoSpec;

@Service
public class TradeServiceImpl implements TradeService {
	TradeDAO tradeDao;
	
	@Autowired
	public TradeServiceImpl(TradeDAO tradeDao) {
		this.tradeDao = tradeDao;
	}
	
	@Override
	public List<TradeInfo> getTradeList(Map<String, Object> map) {
		return tradeDao.selectTradeListWithDongCode(map);
	}
	
	@Override
	public List<TradeInfo> getAptListWithCoord(Map<String, Object> map) {
		return tradeDao.selectAptListWithCoord(map);
	}

	@Override
	public TradeInfoSpec getTradeSpecWithAptCode(String aptCode) {
		return tradeDao.selectTradeListWithAptCode(aptCode);
	}

	@Override
	public List<DongInfo> getDongInfoListWithCoord(Map<String, Object> map) {
		return tradeDao.selectDongInfoListWithCoord(map);
	}

	@Override
	public List<GugunInfo> getGugunInfoListWithCoord(Map<String, Object> map) {
		return tradeDao.selectGugunInfoListWithCoord(map);
	}
}
