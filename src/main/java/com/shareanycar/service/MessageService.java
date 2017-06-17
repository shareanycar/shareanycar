package com.shareanycar.service;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.dao.MessageDao;
import com.shareanycar.enums.MessageStatus;
import com.shareanycar.model.Booking;
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

	public void delete(User user, List<Message> messages) throws Exception {
		for (Message m : messages) {
			if (user.getId() != m.getFromUser().getId() && user.getId() != m.getToUser().getId()) {
				throw new Exception("some messages do not belong to current user");
			}
		}

		for (Message m : messages) {
			messageDao.delete(m);
		}
	}

	public void bookingMessage(String msgTitlePrefix, Booking booking) throws Exception {

		if (booking.getCar() == null) {
			throw new Exception("car is not set");
		}

		String title = msgTitlePrefix + " [" + booking.getCar().getManufacturer().getName() + " "
				+ booking.getCar().getModelName() + "] " + booking.getDateFrom() + " - " + booking.getDateTo();

		String note = booking.getNote() == null ? " empty tex" : booking.getNote();

		String text = "note from " + booking.getUser().getFirstName() + " " + booking.getUser().getLastName() + ": "
				+ note;

		User fromUser = booking.getCar().getUser();
		User toUser = booking.getUser();

		if (fromUser == null || toUser == null) {
			throw new Exception("owner or client is not set");
		}

		Message message = new Message(title, text, fromUser, toUser, MessageStatus.NEW);
		send(fromUser, message);

	}

	public void carBooked(Booking booking) throws Exception {
		bookingMessage("Car booked:", booking);
	}

	public void bookingConfirmed(Booking booking) throws Exception {
		bookingMessage("Booking has been confirmed:", booking);
	}

	public void bookingCanceled(Booking booking) throws Exception {
		bookingMessage("Booking has been canceled:", booking);
	}
}
