package com.shareanycar.dao;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.User;

@Service
public class UserDao extends BasicDao<User> {

	public UserDao() {
		super("User");
	}

	@Inject
	public ExtDao<?> extDao;

	public User findByEmail(String email) {
		return (User) extDao.findOneByParam("from User where email = :email", "email", email);

	}

	public User findByToken(String token) {
		return (User) extDao.findOneByParam("from User where token = :token", "token", token);

	}

	public User findByActivationToken(String activationToken) {
		return (User) extDao.findOneByParam("from User where activationToken = :activationToken", "activationToken",
				activationToken);
	}
}
