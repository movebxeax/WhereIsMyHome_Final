package com.whereismyhome.model.dao.tradeinfo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.tradeinfo.CommercialInfo;

@Mapper
public interface CommercialDAO {
	
	List<CommercialInfo> getCommercialList(String dongCode);
}
