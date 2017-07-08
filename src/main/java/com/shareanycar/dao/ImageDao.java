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
	
	@SuppressWarnings("unchecked")
	public List<Image> findByCarId(Long id){
		return (List<Image>) extDao.findListByParam("from Image where car_id = :id", "id", id);
	}
	
	public Image findMainByCarId(Long id){
		return (Image) extDao.findOneByParam("from Image where car_id = :id and main = 1", "id", id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Image> findNotMainByCarId(Long id){
		return (List<Image>) extDao.findListByParam("from Image where car_id = :id and main = 0", "id", id);
	}

}
