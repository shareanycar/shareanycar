package com.shareanycar.service;

import javax.inject.Inject;

import com.shareanycar.dao.UserDao;
import com.shareanycar.model.User;
import com.shareanycar.util.MiscUtils;

public class AuthService {
	
	
	
	@Inject
	public UserDao userDao;
	
	@Inject
	public MiscUtils miscUtils;
	
	
	public User authenticateUser(String token){
		User user = userDao.findByToken(token);
		return user;
	}
	
	public User authenticateUser(String email, String password){
		User user = userDao.findByEmail(email);
		if(user == null) return null;
		
		if(user.getEmail().equals(email) && user.getPassword().equals(password)){

			if(user.getToken() == null){
				user.setToken(miscUtils.randonString());
				user = userDao.save(user);
			}
			return user;
		}
		return null;
	}
	
}
