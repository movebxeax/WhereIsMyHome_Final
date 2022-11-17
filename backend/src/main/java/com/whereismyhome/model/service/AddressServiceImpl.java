package com.whereismyhome.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismyhome.model.dao.AddressDAO;
import com.whereismyhome.model.dto.AddressInfo;
import com.whereismyhome.model.dto.DistrictInfo;

@Service
public class AddressServiceImpl implements AddressService {
	private AddressDAO addressDao;

	@Autowired
	public AddressServiceImpl(AddressDAO addressDao) {
		this.addressDao = addressDao;
	}
	
	public List<DistrictInfo> getSidoList() {
		return addressDao.selectSidoList();
	}
	public List<DistrictInfo> getGugunList(String sidoCode){
		return addressDao.selectGugunList(sidoCode);
	}
	public List<DistrictInfo> getDongList(String gugunCode){
		return addressDao.selectDongList(gugunCode);
	}

	@Override
	public List<AddressInfo> searchBaseAddressListWithKeyword(String keyword) {
		return addressDao.selectAddressListWithKeyword(keyword);
	}
}
