package com.shareanycar.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.dao.MessageDao;
import com.shareanycar.enums.MessageStatus;
import com.shareanycar.model.Message;
import com.shareanycar.model.User;

@Service
public class MessageService {
	
	@Inject
	public MessageDao messageDao;
	
	public void send(User fromUser, Message message){
		message.setFromUser(fromUser);
		messageDao.save(message);
	}
	
	public List<Message> incoming(Long toUserId){
		return messageDao.findByToUserId(toUserId);
	}
	
	public List<Message> outgoing(Long fromUserId){
		return messageDao.findByFromUserId(fromUserId);
	}
	
	public Message read(User user, Long messageId) throws Exception{
		Message message = messageDao.findOne(messageId);
		if(message == null){
			throw new Exception("can not find message");
		}
		
		if(message.getToUser().getId() != user.getId() && message.getFromUser().getId()  != user.getId()){
			throw new Exception("message does not belong to current user");
		}
		
		message.setMessageStatus(MessageStatus.READ);
		message = messageDao.save(message);
		return message;
	}
}
