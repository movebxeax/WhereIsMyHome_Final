package com.whereismyhome.model.service.user;

import java.util.Map;

import com.whereismyhome.model.dto.user.UserInfo;
import com.whereismyhome.model.dto.user.UserInfoDetail;

public interface UserService {
	public UserInfo login(UserInfo userInfo);
	public boolean signup(UserInfoDetail userInfo);
	public UserInfoDetail getUserInfo(String userid);
	public boolean modifyUserInfo(UserInfoDetail userInfo);
	public boolean deleteUserInfo(String userid);
	public boolean setInterestDistrict(Map<String, String> map);
	
	public UserInfo findUserByUserId(String userid);
	public boolean saveRefreshToken(Map<String, String> map);
	public String findUserIdByRefreshToken(String token);
}
