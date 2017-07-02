package com.shareanycar.service;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;

import com.shareanycar.dao.UserDao;
import com.shareanycar.enums.UserStatus;
import com.shareanycar.model.Car;
import com.shareanycar.model.User;
import com.shareanycar.util.MiscUtils;

@Service
public class UserService {

	@Inject
	public UserDao userDao;

	@Inject
	public MiscUtils miscUtils;

	@Inject
	public CarService carService;

	@Inject
	public ImageService imageService;

	@Inject
	public Logger logger;

	public Long create(User user) throws Exception {

		if (userDao.findByEmail(user.getEmail()) != null) {
			throw new Exception("user with such email already exists");
		}

		user.setUserStatus(UserStatus.NEW);
		user.setActivationToken(miscUtils.randonString());
		user = userDao.save(user);

		return user.getId();
	}

	public void delete(User user) throws Exception {
		if (user == null) {
			throw new Exception("can not find user");
		}

		long userId = user.getId();
		
		try {
			for (Car car : user.getCars()) {
				carService.delete(user.getId(), car.getId());
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		try {
			imageService.deleteUserImage(user.getId(), true);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		user = userDao.findOne(userId);
		userDao.delete(user);
	}

	public void update(Long userId, User user) throws Exception {
		User currentUser = userDao.findOne(userId);

		if (currentUser == null) {
			throw new Exception("can not find user");
		}

		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setAddress(user.getAddress());
		currentUser.setPhone(user.getPhone());
		currentUser.setPassport(user.getPassport());
		currentUser.setDrivingLicense(user.getDrivingLicense());
		currentUser.setDescription(user.getDescription());

		currentUser = userDao.save(currentUser);
	}

	public void updatePassword(Long userId, String oldPassword, String newPassword) throws Exception {
		User user = userDao.findOne(userId);
		if (user == null) {
			throw new Exception("can not find user");
		}

		if (!user.getPassword().equals(oldPassword)) {
			throw new Exception("wrong password");
		}

		user.setPassword(newPassword);
		userDao.save(user);
	}

	public Long activate(String activationToken) throws Exception {
		User user = userDao.findByActivationToken(activationToken);

		if (user == null) {
			throw new Exception("can not find user");
		}

		user.setUserStatus(UserStatus.ACTIVATED);
		user = userDao.save(user);
		return user.getId();
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public User findById(Long id) {
		return userDao.findOne(id);
	}

}
