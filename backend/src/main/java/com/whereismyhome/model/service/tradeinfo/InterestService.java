package com.whereismyhome.model.service.tradeinfo;

import java.util.List;
import java.util.Map;

import com.whereismyhome.model.dto.tradeinfo.InterestInfo;

public interface InterestService {
	public boolean addInterestInfo(Map<String, Object> map);
	public List<InterestInfo> getInterestInfoList(String name);
	public boolean removeInterestInfo(Map<String, Object> map);
	public InterestInfo getInterestInfo(Map<String, Object> map);
}
