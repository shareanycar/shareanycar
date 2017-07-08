package com.shareanycar.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.hk2.testing.junit.HK2Runner;

import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.ImageDao;
import com.shareanycar.dao.LocationDao;
import com.shareanycar.dao.UserDao;
import com.shareanycar.model.User;

public class UserServiceTest extends HK2Runner {

	@Inject
	public UserService userService;

	@Inject
	public UserDao userDao;

	@Inject
	public CarDao carDao;

	@Inject
	public ImageDao imageDao;

	@Inject
	public LocationDao locationDao;

	@Before
	public void setUp() throws Exception {


		imageDao.deleteAll();

		carDao.deleteAll();
		userDao.deleteAll();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void ownerCreationTest() {

		Long id;
		User user =  new User("Bob", "Johnson", "test@test.com", "letmein");
		
		
		try {
			id = userService.create(user);
			
			user = userService.findById(id);
			assertEquals("user not created", user.getPassword(), "letmein");

		} catch (Exception e1) {
			fail(e1.getMessage());
		}

		try {
			User user2 = new  User("Bob", "Johnson", "test@test.com", "letmein");
			userService.create(user2);
			fail("able to create duplicate user, email is the same");
		} catch (Exception e) {
			
		}

	}
}


