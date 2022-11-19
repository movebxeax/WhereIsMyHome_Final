package com.whereismyhome.model.service.tradeinfo;

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

	public boolean addInterestInfo(InterestInfo interestInfo)
	{
		return interestDao.addInterestInfo(interestInfo) > 0;
	}
	
	public InterestInfo getInterestInfo(String userid)
	{
		return interestDao.getInterestInfo(userid);
	}
}
