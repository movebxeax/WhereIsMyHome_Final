package com.whereismyhome.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismyhome.model.dao.InterestDAO;
import com.whereismyhome.model.dto.InterestInfo;

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
