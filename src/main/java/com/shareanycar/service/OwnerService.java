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
		
		Location setLocation = locationDao.findByCountryAndCity(location.getCountry(), location.getCity());
		
		if( setLocation == null){
			setLocation = locationDao.save(location);
		}
		
		owner.setLocation(setLocation);
		owner = ownerDao.save(owner);
		
		return owner.getId();
	}
	
	
	public void update(Long ownerId, Owner owner, Location location) throws Exception{
		 Owner currentOwner = ownerDao.findOne(ownerId); 
		 
		 if(currentOwner == null){
				throw new Exception("can not find owner with id:" + ownerId);
		 }
		 
		 Location setLocation = locationDao.findByCountryAndCity(location.getCountry(), location.getCity());
			
		 if( setLocation == null){
			setLocation = locationDao.save(location);
		 }
		 
		 currentOwner.setFirstName(owner.getFirstName());
		 currentOwner.setLastName(owner.getLastName());
		 currentOwner.setLocation(setLocation);
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
	


	public Owner findOwnerByEmail(String email) {
		return ownerDao.findOwnerByEmail(email);
	}
	
	
	public Owner findOwnerById(Long id){
		return ownerDao.findOne(id);
	}
	

}
