package com.shareanycar.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.shareanycar.enums.MessageStatus;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime messageDate;

	private String title;

	private String text;

	public Message() {
	}
	
	public Message(String title, String text, User fromUser, User toUser, MessageStatus messageStatus) {
		this.title = title;
		this.text = text;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.messageStatus = messageStatus;
		this.messageDate = LocalDateTime.now();
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "from_user_id")
	private User fromUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "to_user_id")
	private User toUser;

	private MessageStatus messageStatus;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public User getFromUser() {
		return fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public MessageStatus getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(MessageStatus messageStatus) {
		this.messageStatus = messageStatus;
	}

	public LocalDateTime getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(LocalDateTime messageDate) {
		this.messageDate = messageDate;
	}

}
