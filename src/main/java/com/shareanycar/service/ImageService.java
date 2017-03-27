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

	private String saveFile(InputStream is) throws Exception {

		String uploadedFileLocation = appConfig.getImageLocation();
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

	public void reduceImg(String imgsrc, String imgdist, String type, int widthdist, int heightdist) {
		try {
			File srcfile = new File(imgsrc);
			if (!srcfile.exists()) {
				return;
			}

			java.awt.Image src = javax.imageio.ImageIO.read(srcfile);

			BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, java.awt.Image.SCALE_SMOOTH), 0, 0,
					null);
			// tag.getGraphics().drawImage(src.getScaledInstance(widthdist,
			// heightdist, Image.SCALE_AREA_AVERAGING), 0, 0, null);

			FileOutputStream out = new FileOutputStream(imgdist);
			ImageIO.write(tag, type, out);
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Image uploadImage(Long carId, Long ownerId, InputStream is) throws Exception {

		Car car = carDao.findOne(carId);

		if (car == null) {
			throw new Exception("can not find car with id:" + carId);
		}

		if (car.getOwner().getId() != ownerId) {
			throw new Exception(
					"can not load image, car does not belong to current owner id:" + ownerId + "; car id" + carId);
		}

		String fileName = saveFile(is);

		Image image = new Image();

		image.setName(fileName);
		image.setUrl(appConfig.getUrlPrefix() + fileName);
		image.setCar(car);

		image = imageDao.save(image);

		if (car.getMainImageUrl() == null) {
			car.setMainImageUrl(appConfig.getUrlPrefix() + fileName);
			carDao.save(car);
		}

		return image;
	}

	public boolean deleteImage(Long imageId, Long ownerId) throws Exception {
		Image image = imageDao.findOne(imageId);
		if (image == null) {
			throw new Exception("can not find image with id:" + imageId);
		}

		if (image.getCar().getOwner().getId() != ownerId) {
			throw new Exception("image belongs to car that does not belong to current owner id:" + ownerId
					+ "; image id" + imageId);
		}

		File f = new File(appConfig.getImageLocation() + image.getName());
		if (f.delete()) {
			imageDao.delete(image);
			return true;
		} else {
			return false;
		}
	}

	public List<Image> findImageByCarId(Long carId) {
		return imageDao.findImageByCarId(carId);
	}
}
