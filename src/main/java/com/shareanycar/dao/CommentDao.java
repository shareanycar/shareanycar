package com.shareanycar.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Comment;

@Service
public class CommentDao extends BasicDao<Comment> {

	public CommentDao() {
		super("Comment");
	}

	@Inject
	public ExtDao<?> extDao;

	public List<Comment> findCommentByCarId(Long id) {
		return (List<Comment>) extDao.findListByParam("from Comment where car_id = :carId", "car_id", id);
	}

	public Comment findCommentByCustomerIdAndCarId(Long customerId, Long carId) {
		try {
			return (Comment) extDao.findOneByParams("from Comment where customer_id = :customerId and car_id = :carId",
					new String[] { "customer_id", "car_id" }, new Object[] { customerId, carId });
		} catch (Exception e) {
			return null;
		}
	}

}
