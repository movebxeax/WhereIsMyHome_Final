package com.whereismyhome.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.UserInfo;

@Mapper
public interface UserDAO {
	UserInfo login(UserInfo userInfo);
	int signup(UserInfo userInfo);	
	UserInfo getUserInfo(String userid);	
	int modifyUserInfo(UserInfo userInfo);	
	int deleteUserInfo(String userid);
	//int setInterestDistrict(String dongCode, String userid);
	int setInterestDistrict(Map<String, String> map);
}
