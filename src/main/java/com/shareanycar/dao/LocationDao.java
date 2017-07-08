package com.shareanycar.dao;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Location;

@Service
public class LocationDao extends BasicDao<Location> {

	public LocationDao() {
		super("Location");
	}

	@Inject
	public ExtDao<?> extDao;

	public Location findByCountryAndCity(String country, String city) {
		try {
			return (Location) extDao.findOneByParams("from Location where country = :country and city = :city",
					new String[] { "country", "city" }, new Object[] { country, city });
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Location> findByCountry(String country) {
		return (List<Location>) extDao.findListByParam("from Location where country = :country", "country", country);
	}

}
