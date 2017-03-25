package com.shareanycar.dao;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Message;

@Service
public class MessageDao extends BasicDao<Message>{

	public MessageDao() {
		super("Message");
	}

}
