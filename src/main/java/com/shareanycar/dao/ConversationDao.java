package com.shareanycar.dao;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Conversation;

@Service
public class ConversationDao extends BasicDao<Conversation>{

	public ConversationDao() {
		super("Conversation");
	}

}
