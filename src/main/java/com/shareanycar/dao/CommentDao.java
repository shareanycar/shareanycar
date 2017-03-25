package com.shareanycar.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Comment;

@Service
public class CommentDao extends BasicDao<Comment>{

	public CommentDao() {
		super("Comment");
	}
	
	public List<Comment> findCommentByCarId(Long carId){
		Session session = SessionUtil.getSession();
		   Query<Comment> query = session.createQuery("from Comment where car_id = :carId");
		   query.setParameter("carId", carId);
		   List<Comment> comment =  query.getResultList();
		   session.close();
		   return comment;
	}

	public Comment findCommentByCustomerIdAndCarId(Long customerId, Long carId){
		Session session = SessionUtil.getSession();
		   Query<Comment> query = session.createQuery("from Comment where customer_id = :customerId and car_id = :carId");
		   query.setParameter("customerId", customerId);
		   query.setParameter("carId", carId);
		   Comment comment;
		   try{
			   comment =  query.getSingleResult();
		   }catch(NoResultException e){
			   comment = null;
		   }
		   session.close();
		   return comment;
	}

	
}
