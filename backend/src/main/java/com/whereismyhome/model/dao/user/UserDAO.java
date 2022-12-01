package com.whereismyhome.model.dao.user;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.user.UserInfo;
import com.whereismyhome.model.dto.user.UserInfoDetail;

@Mapper
public interface UserDAO {
	//int setInterestDistrict(String dongCode, String userid);
	int setInterestDistrict(Map<String, String> map);
	
	
	//===========================
	int signup(UserInfoDetail userInfo);	
	UserInfo login(UserInfo userInfo);
	UserInfo selectUserByUserId(String userid);
	UserInfoDetail selectUserInfoDetails(String userid);	
	int updateUserInfoDetails(UserInfoDetail userInfo);
	int insertRefreshToken(Map<String, String> map);
	String selectUserIdByRefreshToken(String token);
	int deleteUserInfo(String userid);
}
