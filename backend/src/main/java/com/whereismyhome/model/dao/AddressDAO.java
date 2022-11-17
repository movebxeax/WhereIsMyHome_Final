package com.whereismyhome.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.AddressInfo;
import com.whereismyhome.model.dto.DistrictInfo;

@Mapper
public interface AddressDAO {

	List<DistrictInfo> selectSidoList();
	List<DistrictInfo> selectGugunList(String sidoCode);
	List<DistrictInfo> selectDongList(String gugunCode);
	List<AddressInfo> selectAddressListWithKeyword(String keyword);
}
