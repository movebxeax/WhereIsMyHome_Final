package com.whereismyhome.model.service;

import java.util.List;

import com.whereismyhome.model.dto.AddressInfo;
import com.whereismyhome.model.dto.DistrictInfo;

public interface AddressService {	
	public List<DistrictInfo> getSidoList();
	public List<DistrictInfo> getGugunList(String sidoCode);
	public List<DistrictInfo> getDongList(String gugunCode);
	public List<AddressInfo> searchBaseAddressListWithKeyword(String keyword);
}
