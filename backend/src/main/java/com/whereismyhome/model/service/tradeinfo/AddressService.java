package com.whereismyhome.model.service.tradeinfo;

import java.util.List;

import com.whereismyhome.model.dto.tradeinfo.AddressInfo;
import com.whereismyhome.model.dto.tradeinfo.DistrictInfo;

public interface AddressService {	
	public List<DistrictInfo> getSidoList();
	public List<DistrictInfo> getGugunList(String sidoCode);
	public List<DistrictInfo> getDongList(String gugunCode);
	public List<AddressInfo> searchBaseAddressListWithKeyword(String keyword);
}
