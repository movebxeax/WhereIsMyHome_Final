package com.whereismyhome.model.service;

import java.util.List;

import com.whereismyhome.model.dto.CommercialInfo;

public interface CommercialService {
	public List<CommercialInfo> getCommercialList(String dongCode);
}
