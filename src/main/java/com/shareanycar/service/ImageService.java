package com.shareanycar.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.shareanycar.config.AppConfig;
import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.ImageDao;
import com.shareanycar.dao.UserDao;
import com.shareanycar.dao.UserImageDao;
import com.shareanycar.model.Car;
import com.shareanycar.model.Image;
import com.shareanycar.model.User;
import com.shareanycar.model.UserImage;
import com.shareanycar.util.MiscUtils;

@Service
public class ImageService {

	@Inject
	public CarDao carDao;

	@Inject
	public ImageDao imageDao;
	
	@Inject
	public UserImageDao userImageDao;
	
	@Inject
	public UserDao userDao;

	@Inject
	public AppConfig appConfig;

	@Inject
	public MiscUtils miscUtils;

	private void saveFile(InputStream is, String fileName) throws Exception {

		String uploadedFileLocation = appConfig.getImageLocation();
		
		OutputStream out = new FileOutputStream(new File(uploadedFileLocation + fileName));

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = is.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}

		out.flush();
		out.close();
		
	}

	private void reduceImg(String imgsrc, String imgdist, String type, int widthdist, int heightdist) {
		try {
			File srcfile = new File(imgsrc);
			if (!srcfile.exists()) {
				return;
			}

			java.awt.Image src = javax.imageio.ImageIO.read(srcfile);

			BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, java.awt.Image.SCALE_SMOOTH), 0, 0,
					null);
			
			FileOutputStream out = new FileOutputStream(imgdist);
			ImageIO.write(tag, type, out);
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void uploadCarImage(Long carId, Long userId, InputStream is, boolean main) throws Exception {

		Car car = carDao.findOne(carId);

		if (car == null) {
			throw new Exception("can not find car with id:" + carId);
		}

		if (car.getUser().getId() != userId) {
			throw new Exception(
					"can not load image, car does not belong to current owner id:" + userId + "; car id" + carId);
		}
		
		Image image = new Image();
		
		String fileName ;
		
		if(main){
			image = imageDao.findMainByCarId(carId);
			if(image != null){
				fileName = image.getName();
			}else{
				image = new Image();
				fileName = miscUtils.randonString();
			}
		}else{
			fileName = miscUtils.randonString();
		}

		saveFile(is, fileName);
		
		reduceImg(appConfig.getImageLocation() + fileName, appConfig.getImageLocationLarge() + fileName, "jpg",
				appConfig.getLargeWidth(), appConfig.getLargeHeight());
		
		reduceImg(appConfig.getImageLocation() + fileName, appConfig.getImageLocationMedium() + fileName, "jpg",
				appConfig.getMediumWidth(), appConfig.getMediumHeight());

		reduceImg(appConfig.getImageLocation() + fileName, appConfig.getImageLocationSmall() + fileName, "jpg",
				appConfig.getSmallWidth(), appConfig.getSmallHeight());
		
		File f = new File(appConfig.getImageLocation() + fileName);
		f.delete();

		image.setName(fileName);
		image.setUrlLarge(appConfig.getUrlPrefixLarge() + fileName);
		image.setUrlSmall(appConfig.getUrlPrefixSmall() + fileName);
		image.setUrlMedium(appConfig.getUrlPrefixMedium() + fileName);
		image.setMain(main);
		image.setCar(car);

		image = imageDao.save(image);
	}

	private void validateCarImage(Image image, Long carId, Long userId) throws Exception{
		if(image == null){
			throw new Exception("can not find image" );
		}
		
		if(image.getCar().getId() != carId){
			throw new Exception("image does not belong to car");
		}
		
		if (image.getCar().getUser().getId() != userId) {
			throw new Exception("image belongs to car that does not belong to current owner id:" + userId
					+ "; image id" + image.getId());
		}
	}
	
	public void deleteCarImage(Long imageId,Long carId, Long userId, boolean clearTable) throws Exception {
		Image image = imageDao.findOne(imageId);
		validateCarImage(image,carId,userId);

		File f = new File(appConfig.getImageLocationMedium() + image.getName());
		f.delete(); 
		
		f = new File(appConfig.getImageLocationSmall() + image.getName());
		f.delete();
		
		f = new File(appConfig.getImageLocationLarge() + image.getName());
		f.delete();
		
		if(clearTable){
			imageDao.delete(image);
		}
	}

	public void uploadUserImage(Long userId, InputStream is) throws Exception{
		User user = userDao.findOne(userId);
		if(user == null){
			throw new Exception("can not find user");
		}
		
		UserImage userImage = user.getUserImage();
		String fileName ;
		
		if(userImage == null){
		 userImage = new UserImage(); 
		}else{
			deleteUserImage(user.getId(),false);
		}
		
		fileName = miscUtils.randonString();
		
		saveFile(is, fileName);
		
		reduceImg(appConfig.getImageLocation() + fileName, appConfig.getImageLocationMedium() + fileName, "jpg",
				appConfig.getMediumWidth(), appConfig.getMediumHeight());

		reduceImg(appConfig.getImageLocation() + fileName, appConfig.getImageLocationSmall() + fileName, "jpg",
				appConfig.getSmallWidth(), appConfig.getSmallHeight());
		
		
		File f = new File(appConfig.getImageLocation() + fileName);
		f.delete();
		
		userImage.setName(fileName);
		userImage.setUrlMedium(appConfig.getUrlPrefixMedium() + fileName);
		userImage.setUrlSmall(appConfig.getUrlPrefixSmall() + fileName);
		userImage.setUser(user);
		userImage = userImageDao.save(userImage);
		
		user.setUserImage(userImage);
		user = userDao.save(user);
		
	}
	
	public void deleteUserImage(Long userId, boolean clearTable) throws Exception{
		User user = userDao.findOne(userId);
		UserImage userImage = user.getUserImage();
		
		if(userImage == null){
			throw new Exception("can not find user image");
		}
		
		File f = new File(appConfig.getImageLocationMedium() + userImage.getName());
		f.delete();
		
		f = new File(appConfig.getImageLocationSmall() + userImage.getName());
		f.delete();
		
		if(clearTable){
			user.setUserImage(null);
			user = userDao.save(user);
			userImageDao.delete(userImage);
		}
		
	}
	
	public Image findMainByCarId(Long carId){
		return imageDao.findMainByCarId(carId);
	}
	
	public List<Image> findNotMainByCarId(Long carId){
		return imageDao.findNotMainByCarId(carId);
	}
	
	public List<Image> findByCarId(Long carId) {
		return imageDao.findByCarId(carId);
	}
	
	public Image findById(Long id){
		return imageDao.findOne(id);
	}
}
