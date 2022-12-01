package com.whereismyhome.model.service.tradeinfo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismyhome.model.dao.tradeinfo.InterestDAO;
import com.whereismyhome.model.dto.tradeinfo.InterestInfo;

@Service
public class InterestServiceImpl implements InterestService{
	private InterestDAO interestDao;
	
	@Autowired	
	public InterestServiceImpl(InterestDAO interestDao) {
		this.interestDao = interestDao;
	}

	public boolean addInterestInfo(Map<String, Object> map)
	{
		return interestDao.insertInterestInfo(map) > 0;
	}
	
	public List<InterestInfo> getInterestInfoList(String userid)
	{
		return interestDao.selectInterestInfos(userid);
	}

	@Override
	public boolean removeInterestInfo(Map<String, Object> map) {
		return interestDao.deleteInterestInfo(map) > 0;
	}

	@Override
	public InterestInfo getInterestInfo(Map<String, Object> map) {
		return interestDao.selectInterestInfo(map);
	}
}
