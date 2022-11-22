package com.whereismyhome.model.service.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismyhome.model.dao.user.UserDAO;
import com.whereismyhome.model.dto.user.UserInfo;
import com.whereismyhome.model.dto.user.UserInfoDetail;

@Service
public class UserServiceImpl implements UserService {
	private UserDAO userDao;

	@Autowired
	public UserServiceImpl(UserDAO userDao) {
		this.userDao = userDao;
	}

	public boolean setInterestDistrict(Map<String, String> map) {
		return userDao.setInterestDistrict(map) > 0;
	}

	//==============

	public boolean signup(UserInfoDetail userInfo)
	{
		return userDao.signup(userInfo) > 0;
	}
	
	public UserInfo login(UserInfo userInfo)
	{
		return userDao.login(userInfo);
	}

	public UserInfo findUserByUserId(String userid) {
		return userDao.selectUserByUserId(userid);
	}
	
	public UserInfoDetail getUserInfo(String userid)
	{
		return userDao.selectUserInfoDetails(userid);
	}

	public boolean modifyUserInfo(UserInfoDetail userInfo)
	{
		return userDao.updateUserInfoDetails(userInfo) > 0;
	}
	
	public boolean deleteUserInfo(String userid)
	{
		return userDao.deleteUserInfo(userid) > 0;
	}

	@Override
	public boolean saveRefreshToken(Map<String, String> map) {
		return userDao.insertRefreshToken(map) > 0;
	}

	@Override
	public String findUserIdByRefreshToken(String token) {
		return userDao.selectUserIdByRefreshToken(token);
	}
}
