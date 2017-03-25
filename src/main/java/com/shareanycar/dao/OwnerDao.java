package com.shareanycar.dao;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Owner;

@Service
public class OwnerDao extends BasicDao<Owner> {

	public OwnerDao() {
		super("Owner");
	}
	
	@Inject
	public ExtDao<?> extDao;
	
	public Owner findOwnerByEmail(String email) {
		return (Owner) extDao.findOneByParam("from Owner where email = :email", "email", email);
		
	}
	
	public Owner findOwnerByToken(String token) {
		return (Owner) extDao.findOneByParam("from Owner where token = :token", "token", token);

	}
	
}
