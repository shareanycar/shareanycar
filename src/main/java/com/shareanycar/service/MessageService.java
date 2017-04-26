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

	public void send(User fromUser, Message message) {
		message.setFromUser(fromUser);

		Message messageTo = new Message(message.getTitle(), message.getText(), message.getFromUser(),
				message.getToUser(), MessageStatus.SENT);
		
		Message messageFrom = new Message(message.getTitle(), message.getText(), message.getFromUser(),
				message.getToUser(), MessageStatus.NEW);
		
		messageDao.save(messageTo);
		messageDao.save(messageFrom);
	}

	public Message read(User user, Long messageId) throws Exception {
		Message message = messageDao.findOne(messageId);
		if (message == null) {
			throw new Exception("can not find message");
		}

		if (message.getToUser().getId() != user.getId() && message.getFromUser().getId() != user.getId()) {
			throw new Exception("message does not belong to current user");
		}

		if (message.getMessageStatus() == MessageStatus.NEW) {
			message.setMessageStatus(MessageStatus.READ);
			message = messageDao.save(message);
		}
		return message;
	}
	
	public void delete(User user, List<Message> messages) throws Exception{
		for(Message m: messages){
			if(user.getId() != m.getFromUser().getId() && user.getId() != m.getToUser().getId()){
				throw new Exception("some messages do not belong to current user");
			}
		}
		
		for(Message m : messages){
			messageDao.delete(m);
		}
	}
}
