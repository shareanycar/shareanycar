package com.shareanycar.dto;

import java.time.LocalDateTime;

import com.shareanycar.enums.MessageStatus;

public class MessageInfoDto {
	private Long id;
	private Long fromUserId;
	private Long toUserId;
	private String fromUserName;
	private String toUserName;
	private String title;
	private MessageStatus messageStatus;
	private LocalDateTime messageDate;
	
	public Long getId() {
		return id;
	}
	public Long getFromUserId() {
		return fromUserId;
	}
	public Long getToUserId() {
		return toUserId;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public String getTitle() {
		return title;
	}
	public MessageStatus getMessageStatus() {
		return messageStatus;
	}
	public LocalDateTime getMessageDate() {
		return messageDate;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}
	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setMessageStatus(MessageStatus messageStatus) {
		this.messageStatus = messageStatus;
	}
	public void setMessageDate(LocalDateTime messageDate) {
		this.messageDate = messageDate;
	}
	
	
	
}
