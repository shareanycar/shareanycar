package com.shareanycar.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	private String text;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conversation_id")
	private Conversation conversation;
	
	@NotNull
	private boolean fromCustomer;

	public Long getId() {
		return id;
	}

	public boolean isFromCustomer() {
		return fromCustomer;
	}

	public void setFromCustomer(boolean fromCustomer) {
		this.fromCustomer = fromCustomer;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	
}
