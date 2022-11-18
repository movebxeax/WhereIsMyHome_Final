package com.whereismyhome.model.dao.user;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.user.UserInfo;
import com.whereismyhome.model.dto.user.UserInfoDetail;

@Mapper
public interface UserDAO {
	UserInfo login(UserInfo userInfo);
	UserInfo getUserInfo(String userid);	
	int modifyUserInfo(UserInfo userInfo);	
	int deleteUserInfo(String userid);
	//int setInterestDistrict(String dongCode, String userid);
	int setInterestDistrict(Map<String, String> map);
	
	
	//===========================
	int signup(UserInfoDetail userInfo);	
	UserInfo selectUserByUserId(String userid);
	int insertRefreshToken(Map<String, String> map);
	String selectUserIdByRefreshToken(String token);
}
