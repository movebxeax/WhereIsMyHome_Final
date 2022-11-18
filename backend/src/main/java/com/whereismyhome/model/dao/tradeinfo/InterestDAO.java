package com.whereismyhome.model.dao.tradeinfo;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.tradeinfo.InterestInfo;

@Mapper
public interface InterestDAO {
	public int addInterestInfo(InterestInfo interestInfo);	
	public InterestInfo getInterestInfo(String userid);
}
