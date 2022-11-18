package com.whereismyhome.model.service.tradeinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismyhome.model.dao.tradeinfo.CommercialDAO;
import com.whereismyhome.model.dto.tradeinfo.CommercialInfo;

@Service
public class CommercialServiceImpl implements CommercialService {
	private CommercialDAO commercialDao;
	
	@Autowired
	public CommercialServiceImpl(CommercialDAO commercialDao) {
		this.commercialDao = commercialDao;
	}
	
	public List<CommercialInfo> getCommercialList(String dongCode) {
		return commercialDao.getCommercialList(dongCode);
	}
}
