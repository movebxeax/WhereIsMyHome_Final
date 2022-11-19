package com.whereismyhome.model.service.tradeinfo;

import java.util.List;

import com.whereismyhome.model.dto.tradeinfo.CommercialInfo;

public interface CommercialService {
	public List<CommercialInfo> getCommercialList(String dongCode);
}
