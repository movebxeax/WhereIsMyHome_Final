package com.whereismyhome.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.InterestInfo;

@Mapper
public interface InterestDAO {
	public int addInterestInfo(InterestInfo interestInfo);	
	public InterestInfo getInterestInfo(String userid);
}
