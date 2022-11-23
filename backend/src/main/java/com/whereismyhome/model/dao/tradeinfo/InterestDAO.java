package com.whereismyhome.model.dao.tradeinfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.tradeinfo.InterestInfo;

@Mapper
public interface InterestDAO {
	public int insertInterestInfo(Map<String, Object> map);	
	public List<InterestInfo> selectInterestInfos(String userid);
	public int deleteInterestInfo(Map<String, Object> map);
	public InterestInfo selectInterestInfo(Map<String, Object> map);
}
