package com.shareanycar.dao;

import java.util.List;

import javax.inject.Inject;

import com.shareanycar.model.Message;

public class MessageDao extends BasicDao<Message>{

	@Inject
	public ExtDao<?> extDao;
	
	public MessageDao() {
		super("Message");
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> findByToUserId(Long id){
		return (List<Message>) extDao.findListByParam("from Message where to_user_id = :id", "id", id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> findByFromUserId(Long id){
		return (List<Message>) extDao.findListByParam("from Message where from_user_id = :id", "id", id);
	}
	
	

}
