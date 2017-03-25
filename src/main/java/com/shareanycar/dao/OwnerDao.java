package com.shareanycar.dao;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;

import com.shareanycar.model.Owner;

@Service
public class OwnerDao extends BasicDao<Owner> {

	public OwnerDao() {
		super("Owner");
	}
	
	public Owner findOwnerByEmail(String email) {
		Session session = SessionUtil.getSession();
		Query<Owner> query = session.createQuery("from Owner where email = :email");
		query.setParameter("email", email);
		Owner owner;
		try {
			owner = (Owner) query.getSingleResult();
		} catch (NoResultException e) {
			owner = null;
		}
		session.close();
		return owner;
	}

	public Owner findOwnerByToken(String token) {
		Session session = SessionUtil.getSession();
		Query<Owner> query = session.createQuery("from Owner where token = :token");
		query.setParameter("token", token);
		Owner owner;
		try {
			owner = (Owner) query.getSingleResult();
		} catch (NoResultException e) {
			owner = null;
		}
		session.close();
		return owner;
	}
}
