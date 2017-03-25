package com.shareanycar.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.config.AppConfig;
import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.ImageDao;
import com.shareanycar.model.Car;
import com.shareanycar.model.Image;
import com.shareanycar.util.MiscUtils;

@Service
public class ImageService {
	
	@Inject
	public CarDao carDao;
	
	@Inject
	public ImageDao imageDao;
	
	@Inject
	public AppConfig appConfig;

	@Inject
	public MiscUtils miscUtils;
	
	private String saveFile(InputStream is) throws Exception{
		
		String uploadedFileLocation =  appConfig.getImageLocation();
		String fileName = miscUtils.randonString();
				
		OutputStream out = new FileOutputStream(new File(uploadedFileLocation + fileName));
					
		int read = 0;
		byte[] bytes = new byte[1024];
		
				
		while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
		}
			
		out.flush();
		out.close();
		
		return fileName;
	}

	public Image uploadImage(Long carId, InputStream is) throws Exception {

		Car car = carDao.findOne(carId);

		if (car == null) {
			throw new Exception("can not find car with id:" + carId);
		}

		String fileName = saveFile(is);
		
		Image image = new Image();
		
		image.setName(fileName);
		image.setUrl(appConfig.getUrlPrefix() + fileName);
		image.setCar(car);
		
		image = imageDao.save(image);
		return image;
	}
	
	public boolean deleteImage(Long id){
		return true;
	}
}
