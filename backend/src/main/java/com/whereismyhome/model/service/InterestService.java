package com.whereismyhome.model.service;

import com.whereismyhome.model.dto.InterestInfo;

public interface InterestService {
	public boolean addInterestInfo(InterestInfo interestInfo);
	public InterestInfo getInterestInfo(String userid);
}
