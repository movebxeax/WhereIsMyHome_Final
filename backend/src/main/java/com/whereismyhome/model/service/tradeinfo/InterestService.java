package com.whereismyhome.model.service.tradeinfo;

import com.whereismyhome.model.dto.tradeinfo.InterestInfo;

public interface InterestService {
	public boolean addInterestInfo(InterestInfo interestInfo);
	public InterestInfo getInterestInfo(String userid);
}
