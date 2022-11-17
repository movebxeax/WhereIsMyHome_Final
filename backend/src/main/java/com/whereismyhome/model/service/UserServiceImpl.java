package com.whereismyhome.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismyhome.model.dao.UserDAO;
import com.whereismyhome.model.dto.UserInfo;

@Service
public class UserServiceImpl implements UserService {
	private UserDAO userDao;

	@Autowired
	public UserServiceImpl(UserDAO userDao) {
		this.userDao = userDao;
	}	

	public UserInfo login(UserInfo userInfo)
	{
		return userDao.login(userInfo);
	}	

	public boolean signup(UserInfo userInfo)
	{
		return userDao.signup(userInfo) > 0;
	}

	public UserInfo getUserInfo(String userid)
	{
		return userDao.getUserInfo(userid);
	}

	public boolean modifyUserInfo(UserInfo userInfo)
	{
		return userDao.modifyUserInfo(userInfo) > 0;
	}

	public boolean deleteUserInfo(String userid)
	{
		return userDao.deleteUserInfo(userid) > 0;
	}

	public boolean setInterestDistrict(Map<String, String> map) {
		return userDao.setInterestDistrict(map) > 0;
	}
}
