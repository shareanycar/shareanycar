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
import com.sun.javafx.logging.Logger;

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
		
		Owner owner = ownerService.create("FirstName", "LastName", "USA", "New York", "7(777)777-77-77", "test@test.com", "letmein");
		
		assertEquals("user not created", owner.getPassword(), "letmein");

		try {
			ownerService.create("FirstName", "LastName", "USA", "New York", "7(777)777-77-77", "test@test.com", "letmein");
			fail("able to create duplicate user, email is the same");
		} catch (Exception e) {
		}
		
		Location location = owner.getLocation();
		assertNotNull(location);
		assertEquals("location created", "USA",location.getCountry());
	}

	
	
	@Test
	public void twoLocationsCreatedTest(){
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
	}
/*
	
	@Test
	public void createCarImageTest(){
		Owner owner = ownerService.createOwner("FirstName", "LastName", "USA", " New  York ", "7(777)777-77-77", "test@test1.com", "letmein");
		Car car = ownerService.createCar(owner.getId(), "BMW", "new", 2016, "USA", "New York");
		owner = ownerService.findOwnerByEmail("test@test1.com");
		
		Image img1 = ownerService.createCarImage(owner.getId(), car.getId(), "url1");
		Image img2 = ownerService.createCarImage(owner.getId(), car.getId(), "url2");
		
		assertNotNull("Image 1 created", img1);
		assertNotNull("Image 2 created", img2);
		
		assertEquals("Image 1 url", "url1", img1.getUrl());
		assertEquals("Image 2 url", "url2", img2.getUrl());
		
		assertEquals("belongs to the same car", img1.getCar().getId(), img2.getCar().getId());
	}
	
	@Test
	public void removeCarImageTest(){
		Owner owner = ownerService.createOwner("FirstName", "LastName", "USA", " New  York ", "7(777)777-77-77", "test@test1.com", "letmein");
		Car car = ownerService.createCar(owner.getId(), "BMW", "new", 2016, "USA", "New York");
		owner = ownerService.findOwnerByEmail("test@test1.com");
		
		Image img1 = ownerService.createCarImage(owner.getId(), car.getId(), "url1");
		assertNotNull("image created", img1);
		
		owner = ownerService.findOwnerByEmail("test@test1.com");
		car = owner.getCars().iterator().next();
		
		assertTrue("image removed", ownerService.removeImage(owner.getId(), car.getId(), img1.getId()));
		
	}
	*/
}

