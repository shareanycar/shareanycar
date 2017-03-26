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

public class OwnerServiceTest extends HK2Runner{
	
	@Inject
	public OwnerService ownerService ;
	
	@Inject
	public OwnerDao ownerDao ;
	
	@Inject
	public CarDao carDao ;
	
	@Inject
	public ImageDao imageDao ;
	
	@Inject
	public LocationDao locationDao ;
	
	@Inject
	public ConversationDao conversationDao ;
	
	@Inject
	public MessageDao messageDao ;
	
	@Inject
	public CommentDao commentDao ;
	
	@Before
	public void setUp() throws Exception {
		
		messageDao.deleteAll();
		conversationDao.deleteAll();
		imageDao.deleteAll();
		commentDao.deleteAll();
		carDao.deleteAll();
		ownerDao.deleteAll();
		locationDao.deleteAll();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void ownerCreationTest() {
		
		Long id;
		Owner owner;
		try {
			id = ownerService.create("FirstName", "LastName", "USA", "New York", "7(777)777-77-77", "test@test.com", "letmein");
			owner = ownerService.findOwnerById(id);
			assertEquals("user not created", owner.getPassword(), "letmein");

			Location location = owner.getLocation();
			assertNotNull(location);
			assertEquals("location created", "USA",location.getCountry());
			
		} catch (Exception e1) {
			fail("can not create owner");
		}

		try {
			ownerService.create("FirstName", "LastName", "USA", "New York", "7(777)777-77-77", "test@test.com", "letmein");
			fail("able to create duplicate user, email is the same");
		} catch (Exception e) {
		}
		
	}

	
	
	@Test
	public void twoLocationsCreatedTest() {
		
		try {
			ownerService.create("FirstName", "LastName", "USA", " New  York ", "7(777)777-77-77", "test@test1.com", "letmein");
			ownerService.create("FirstName", "LastName", "USA", "Chicago", "7(777)777-77-77", "test@test2.com", "letmein");
			
			Iterable<Location> locations = locationDao.findAll();
			
			int cnt = 0;
			
			for(Location loc : locations){
				cnt++;
				if(loc.getCity().equals("NEW YORK")){
					continue;
				}
				
				if(loc.getCity().equals("CHICAGO")){
					continue;
				}
				fail("did not save location properly");
			}
			
			assertEquals("2 locations expected", 2, cnt);
		} catch (Exception e) {
			fail("can not create owner");
		}
		
	}

}

