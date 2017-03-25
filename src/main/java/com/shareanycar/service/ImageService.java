package com.shareanycar.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.ImageDao;
import com.shareanycar.model.Car;
import com.shareanycar.model.Image;

@Service
public class ImageService {
	@Inject
	public CarDao carDao;
	@Inject
	public ImageDao imageDao;

	private String saveFile(InputStream uploadedInputStream) throws Exception{
		String uploadedFileLocation = "123";
		
		
		OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
					
		int read = 0;
		byte[] bytes = new byte[1024];
		
				
		while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
		}
			
		out.flush();
		out.close();
		
		return uploadedFileLocation;
	}

	public void uploadCarImage(Long carId, InputStream uploadedInputStream) throws Exception {

		Car car = carDao.findOne(carId);

		if (car == null) {
			throw new Exception("can not find car with id:" + carId);
		}

		String fileName = saveFile(uploadedInputStream);
		Image image = new Image();
		image.setName(fileName);
		image.setUrl(fileName);
		imageDao.save(image);
	}
}
