package com.shareanycar.dao;

import java.util.List;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.model.Image;

@Service
public class ImageDao extends BasicDao<Image>{

	@Inject 
	public ExtDao<?> extDao;
	
	public ImageDao() {
		super("Image");
	}
	
	public List<Image> findImageByCarId(Long id){
		return (List<Image>) extDao.findListByParam("from Image where car_id = :id", "id", id);
	}

}
