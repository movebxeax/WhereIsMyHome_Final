package com.whereismyhome.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.CommercialInfo;

@Mapper
public interface CommercialDAO {
	
	List<CommercialInfo> getCommercialList(String dongCode);
}
