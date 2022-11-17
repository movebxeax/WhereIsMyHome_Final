package com.whereismyhome.model.service;

import java.util.Map;

import com.whereismyhome.model.dto.UserInfo;

public interface UserService {
	public UserInfo login(UserInfo userInfo);
	public boolean signup(UserInfo userInfo);
	public UserInfo getUserInfo(String userid);
	public boolean modifyUserInfo(UserInfo userInfo);
	public boolean deleteUserInfo(String userid);
	public boolean setInterestDistrict(Map<String, String> map);
}
