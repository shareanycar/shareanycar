package com.shareanycar.service;

import javax.inject.Inject;

import com.shareanycar.dao.ConversationDao;
import com.shareanycar.dao.MessageDao;
import com.shareanycar.model.Conversation;
import com.shareanycar.model.Customer;
import com.shareanycar.model.Message;
import com.shareanycar.model.Owner;


public class MessageService {
	@Inject
	public ConversationDao conversationDao;
	
	@Inject
	public MessageDao messageDao;
	
	public Conversation startConversation(Customer customer, Owner owner, String title){
		Conversation conversation = new Conversation();
		conversation.setCustomer(customer);
		conversation.setOwner(owner);
		conversation.setTitle(title);
		conversation = conversationDao.save(conversation);
		
		return conversation;
	}
	
	public Message sendMessage(Customer customer, Owner owner, Conversation conversation, boolean fromCustomer, String messageTest){
		if(conversation.getCustomer().getId() == customer.getId() && conversation.getOwner().getId() == owner.getId()){
			Message message = new Message();
			message.setConversation(conversation);
			message.setFromCustomer(fromCustomer);
			message.setText(messageTest);
			message = messageDao.save(message);
			return message;
		}
		return null;
	}
}


