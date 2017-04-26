package com.shareanycar.dto;

import java.time.LocalDateTime;
import java.util.Comparator;

import com.shareanycar.enums.MessageStatus;

public class MessageInfoDto {
	private Long id;
	private Long fromUserId;
	private Long toUserId;
	private String fromUserName;
	private String toUserName;
	private String title;
	private MessageStatus messageStatus;
	private String messageDate;

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

	public String getMessageDate() {
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

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}

	public static class ById implements Comparator<MessageInfoDto> {

		public int compare(MessageInfoDto o1, MessageInfoDto o2) {
			if (o1.getId() < o2.getId()) {
				return 1;
			} else if (o1.getId() > o2.getId()) {
				return -1;
			} else {
				return 0;
			}
		}

	}

	
}
