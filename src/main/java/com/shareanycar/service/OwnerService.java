package com.shareanycar.service;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.ImageDao;
import com.shareanycar.dao.LocationDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.model.Location;
import com.shareanycar.model.Owner;

@Service
public class OwnerService {

	@Inject
	public OwnerDao ownerDao ;

	@Inject
	public CarDao carDao ;
	@Inject
	public LocationDao locationDao ;

	@Inject
	public LocationService locationService ;
	
	@Inject
	public ImageDao imageDao ;

	public Long create(Owner owner, Location location) throws Exception{
		
		if(ownerDao.findOwnerByEmail(owner.getEmail()) != null){
			throw new Exception("user with such email already exists");
		}
		
		location = locationDao.findByCountryAndCity(location.getCountry(), location.getCity());
		
		if( location == null){
			throw new Exception("can not find location");
		}
		
		owner.setLocation(location);
		owner = ownerDao.save(owner);
		
		return owner.getId();
	}
	
	
	public void update(Long ownerId, Owner owner, Location location) throws Exception{
		 Owner currentOwner = ownerDao.findOne(ownerId); 
		 
		 if(currentOwner == null){
				throw new Exception("can not find owner with id:" + ownerId);
		 }
		 
		 location = locationDao.findByCountryAndCity(location.getCountry(), location.getCity());
			
		 if( location == null){
				throw new Exception("can not find location");
		 }
		 
		 currentOwner.setFirstName(owner.getFirstName());
		 currentOwner.setLastName(owner.getLastName());
		 currentOwner.setLocation(location);
		 currentOwner.setPhone(owner.getPhone());
		 
		 ownerDao.save(currentOwner);
	}
	
	public void updatePassword(Long ownerId, String oldPassword, String newPassword) throws Exception{
		Owner owner = ownerDao.findOne(ownerId);
		if(owner == null){
			throw new Exception("can not find owner with id:" + ownerId);
		}
		
		if(!owner.getPassword().equals(oldPassword)){
			throw new Exception("wrong password");
		}
		
		owner.setPassword(newPassword);
		ownerDao.save(owner);
			
	}
	


	public Owner findByEmail(String email) {
		return ownerDao.findOwnerByEmail(email);
	}
	
	
	public Owner findById(Long id){
		return ownerDao.findOne(id);
	}
	

}
