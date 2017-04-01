package com.shareanycar.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.hk2.testing.junit.HK2Runner;

import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.CommentDao;
import com.shareanycar.dao.ConversationDao;
import com.shareanycar.dao.ImageDao;
import com.shareanycar.dao.LocationDao;
import com.shareanycar.dao.MessageDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.model.Location;
import com.shareanycar.model.Owner;

public class OwnerServiceTest extends HK2Runner {

	@Inject
	public OwnerService ownerService;

	@Inject
	public OwnerDao ownerDao;

	@Inject
	public CarDao carDao;

	@Inject
	public ImageDao imageDao;

	@Inject
	public LocationDao locationDao;

	@Inject
	public ConversationDao conversationDao;

	@Inject
	public MessageDao messageDao;

	@Inject
	public CommentDao commentDao;

	@Before
	public void setUp() throws Exception {

		messageDao.deleteAll();
		conversationDao.deleteAll();
		imageDao.deleteAll();
		commentDao.deleteAll();
		carDao.deleteAll();
		ownerDao.deleteAll();
		//locationDao.deleteAll();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void ownerCreationTest() {

		Long id;
		Owner owner =  new Owner.Builder()
							.setFirstName("First Name")
							.setLastName("last name")
							.setEmail("email")
							.setPassword("letmein")
							.setPhone("777777777")
							.build();
		
		Location location = new Location.Builder()
								.setCountry("Russia")
								.setCity("Moscow")
								.build();
		try {
			id = ownerService.create(owner, location);
			
			owner = ownerService.findById(id);
			assertEquals("user not created", owner.getPassword(), "letmein");

			location = owner.getLocation();
			assertNotNull(location);
			assertEquals("location set", "Russia", location.getCountry());

		} catch (Exception e1) {
			fail(e1.getMessage());
		}

		try {
			Owner owner2 = new Owner.Builder().setEmail("email").build();
			ownerService.create(owner2, location);
			fail("able to create duplicate user, email is the same");
		} catch (Exception e) {
			
		}

	}

	
}
