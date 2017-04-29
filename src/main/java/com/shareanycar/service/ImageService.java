package com.shareanycar.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

	@Inject
	public AmazonService amazonService;

	private void saveFile(InputStream is, String fileName) throws Exception {

		String uploadedFileLocation = appConfig.getTmpLocation();

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

	private void moveImageToAmazon(String origFilePath, String key) {
		File f = new File(origFilePath);
		amazonService.uploadFile(key, f);
		f.delete();
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

		Image image;

		String fileName;

		if (main) {
			image = imageDao.findMainByCarId(carId);
			if (image != null) {
				fileName = image.getName();
			} else {
				image = new Image();
				fileName = miscUtils.randonString();
			}
		} else {
			image = new Image();
			fileName = miscUtils.randonString();
		}

		boolean userImage = false;
		saveFile(is, fileName);
		saveResizedImgsRemoveOrig(fileName, userImage);

		String urlPrefix;
		if (appConfig.isLocal()) {
			urlPrefix = appConfig.getUrlPrefix();
			moveResizedImgs(fileName, userImage);
		} else {
			urlPrefix = appConfig.getAmazonUrlPrefix();
			moveResizedImgsToAmazon(fileName, userImage);
		}

		image.setName(fileName);
		image.setUrlLarge(urlPrefix + appConfig.getLargeLocation() + fileName);
		image.setUrlSmall(urlPrefix + appConfig.getSmallLocation() + fileName);
		image.setUrlMedium(urlPrefix + appConfig.getMediumLocation() + fileName);
		image.setMain(main);
		image.setCar(car);

		image = imageDao.save(image);
	}

	private void moveResizedImgs(String fileName, boolean isUserImage) throws IOException {
		Path source;
		Path target;
		if (!isUserImage) {
			source = Paths.get(appConfig.getTmpLocation() + appConfig.getLargeLocation() + fileName);
			target = Paths.get(appConfig.getRootLocation() + appConfig.getLargeLocation() + fileName);
			Files.move(source, target);
		}

		source = Paths.get(appConfig.getTmpLocation() + appConfig.getMediumLocation() + fileName);
		target = Paths.get(appConfig.getRootLocation() + appConfig.getMediumLocation() + fileName);
		Files.move(source, target);

		source = Paths.get(appConfig.getTmpLocation() + appConfig.getSmallLocation() + fileName);
		target = Paths.get(appConfig.getRootLocation() + appConfig.getSmallLocation() + fileName);
		Files.move(source, target);
	}

	private void moveResizedImgsToAmazon(String fileName, boolean isUserImage) {
		String filePath;
		String destFile;
		if (!isUserImage) {
			filePath = appConfig.getTmpLocation() + appConfig.getLargeLocation() + fileName;
			destFile = appConfig.getLargeLocation() + fileName;
			moveImageToAmazon(filePath, fileName);
		}

		filePath = appConfig.getTmpLocation() + appConfig.getMediumLocation() + fileName;
		destFile = appConfig.getMediumLocation() + fileName;
		moveImageToAmazon(filePath, destFile);

		filePath = appConfig.getTmpLocation() + appConfig.getSmallLocation() + fileName;
		destFile = appConfig.getSmallLocation() + fileName;
		moveImageToAmazon(filePath, destFile);
	}

	private void saveResizedImgsRemoveOrig(String fileName, boolean isUserImage) {
		String fileLocation = appConfig.getTmpLocation() + fileName;
		String destFileLocation;

		if (!isUserImage) {

			destFileLocation = appConfig.getTmpLocation() + appConfig.getLargeLocation() + fileName;
			reduceImg(fileLocation, destFileLocation, "jpg", appConfig.getLargeWidth(), appConfig.getLargeHeight());
		}

		destFileLocation = appConfig.getTmpLocation() + appConfig.getMediumLocation() + fileName;
		reduceImg(fileLocation, destFileLocation, "jpg", appConfig.getMediumWidth(), appConfig.getMediumHeight());

		destFileLocation = appConfig.getTmpLocation() + appConfig.getSmallLocation() + fileName;
		reduceImg(fileLocation, destFileLocation, "jpg", appConfig.getSmallWidth(), appConfig.getSmallHeight());

		File f = new File(appConfig.getTmpLocation() + fileName);
		f.delete();
	}

	private void validateCarImage(Image image, Long carId, Long userId) throws Exception {
		if (image == null) {
			throw new Exception("can not find image");
		}

		if (image.getCar().getId() != carId) {
			throw new Exception("image does not belong to car");
		}

		if (image.getCar().getUser().getId() != userId) {
			throw new Exception("image belongs to car that does not belong to current owner id:" + userId + "; image id"
					+ image.getId());
		}
	}

	public void deleteCarImage(Long imageId, Long carId, Long userId, boolean clearTable) throws Exception {
		Image image = imageDao.findOne(imageId);
		validateCarImage(image, carId, userId);

		boolean userImage = false;
		if (appConfig.isLocal()) {
			removeLocalImgs(image.getName(), userImage);
		} else {
			removeAmazonImgs(image.getName(), userImage);
		}

		if (clearTable) {
			imageDao.delete(image);
		}
	}

	private void removeAmazonImgs(String fileName, boolean isUserImage) {
		String filePath;
		if (!isUserImage) {
			filePath = appConfig.getLargeLocation() + fileName;
			amazonService.removeFile(filePath);
		}

		filePath = appConfig.getSmallLocation() + fileName;
		amazonService.removeFile(filePath);

		filePath = appConfig.getMediumLocation() + fileName;
		amazonService.removeFile(filePath);
	}

	private void removeLocalImgs(String fileName, boolean isUserImage) {
		String filePath;
		File f;

		if (!isUserImage) {
			filePath = appConfig.getRootLocation() + appConfig.getMediumLocation() + fileName;
			f = new File(filePath);
			f.delete();
		}

		filePath = appConfig.getRootLocation() + appConfig.getSmallLocation() + fileName;
		f = new File(filePath);
		f.delete();

		filePath = appConfig.getRootLocation() + appConfig.getLargeLocation() + fileName;
		f = new File(filePath);
		f.delete();
	}

	public void uploadUserImage(Long userId, InputStream is) throws Exception {
		User user = userDao.findOne(userId);
		if (user == null) {
			throw new Exception("can not find user");
		}

		UserImage userImage = user.getUserImage();
		String fileName;

		if (userImage == null) {
			userImage = new UserImage();
		} else {
			boolean clearTable = false;
			deleteUserImage(user.getId(), clearTable);
		}

		fileName = miscUtils.randonString();

		boolean isUserImage = true;
		
		saveFile(is, fileName);
		saveResizedImgsRemoveOrig(fileName, isUserImage);

		String urlPrefix ;
		if (appConfig.isLocal()) {
			urlPrefix = appConfig.getUrlPrefix();
			moveResizedImgs(fileName, isUserImage);
		} else {
			urlPrefix = appConfig.getAmazonUrlPrefix();
			moveResizedImgsToAmazon(fileName, isUserImage);
		}

		userImage.setName(fileName);
		userImage.setUrlMedium(urlPrefix + appConfig.getMediumLocation() + fileName);
		userImage.setUrlSmall(urlPrefix + appConfig.getSmallLocation() + fileName);
		userImage.setUser(user);
		userImage = userImageDao.save(userImage);

		user.setUserImage(userImage);
		user = userDao.save(user);

	}

	public void deleteUserImage(Long userId, boolean clearTable) throws Exception {
		User user = userDao.findOne(userId);
		UserImage userImage = user.getUserImage();

		if (userImage == null) {
			throw new Exception("can not find user image");
		}

		boolean isUserImage = true;
		if (appConfig.isLocal()) {
			removeLocalImgs(userImage.getName(), isUserImage);
		} else {
			removeAmazonImgs(userImage.getName(), isUserImage);
		}

		if (clearTable) {
			user.setUserImage(null);
			user = userDao.save(user);
			userImageDao.delete(userImage);
		}
	}

	public Image findMainByCarId(Long carId) {
		return imageDao.findMainByCarId(carId);
	}

	public List<Image> findNotMainByCarId(Long carId) {
		return imageDao.findNotMainByCarId(carId);
	}

	public List<Image> findByCarId(Long carId) {
		return imageDao.findByCarId(carId);
	}

	public Image findById(Long id) {
		return imageDao.findOne(id);
	}
}
