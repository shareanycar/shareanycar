package com.shareanycar.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.hk2.testing.junit.HK2Runner;

import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.ConversationDao;
import com.shareanycar.dao.CustomerDao;
import com.shareanycar.dao.MessageDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.model.Conversation;
import com.shareanycar.model.Customer;
import com.shareanycar.model.Message;
import com.shareanycar.model.Owner;

public class MessageServiceTest extends HK2Runner {

	@Inject
	public CustomerDao customerDao;

	@Inject
	public OwnerDao ownerDao;

	@Inject
	public ConversationDao conversationDao;

	@Inject
	public MessageDao messageDao;

	@Inject
	public CarDao carDao;

	@Inject
	public MessageService messageService;

	@Inject
	public OwnerService ownerService;

	@Inject
	public CustomerService customerService;

	@Before
	public void setUp() throws Exception {
		messageDao.deleteAll();
		conversationDao.deleteAll();
		customerDao.deleteAll();
		carDao.deleteAll();
		ownerDao.deleteAll();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void startConversationTest() {
		Long id;
		try {
			id = ownerService.create("Owner", "Owner", "USA", "New York", "7(777)777-77-77", "test@test.com",
					"letmein");
			Owner owner = ownerService.findOwnerById(id);
			Customer customer = customerService.createCustomer("Customer", "Customer", "7(777)777-77-77",
					"test@test.com", "letmein");
			Conversation conversation = messageService.startConversation(customer, owner, "about new car");

			assertNotNull(conversation);
			assertEquals("title correct", "about new car", conversation.getTitle());

			assertEquals("conversation belongs to customer", customer.getId(), conversation.getCustomer().getId());
			assertEquals("conversation belongs to owner", owner.getId(), conversation.getOwner().getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void sendMessageTest() {
		Long id;
		try {
			id = ownerService.create("Owner", "Owner", "USA", "New York", "7(777)777-77-77", "test@test.com",
					"letmein");
			Owner owner = ownerService.findOwnerById(id);
			
			Customer customer = customerService.createCustomer("Customer", "Customer", "7(777)777-77-77", "test@test.com",
					"letmein");
			Conversation conversation = messageService.startConversation(customer, owner, "about new car");

			Message message = messageService.sendMessage(customer, owner, conversation, true, "test message 1");

			assertEquals("message correct", "test message 1", message.getText());

			assertEquals("right conversation for message", "about new car", message.getConversation().getTitle());

			assertTrue(message.isFromCustomer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
		
	}

}
