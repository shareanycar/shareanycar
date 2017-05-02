package com.shareanycar.config;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.SimpleLogger;

import com.amazonaws.services.s3.AmazonS3;
import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.CarTypeDao;
import com.shareanycar.dao.ExtDao;
import com.shareanycar.dao.FuelTypeDao;
import com.shareanycar.dao.ImageDao;
import com.shareanycar.dao.InsurerDao;
import com.shareanycar.dao.LocationDao;
import com.shareanycar.dao.ManufacturerDao;
import com.shareanycar.dao.MessageDao;
import com.shareanycar.dao.TransmissionTypeDao;
import com.shareanycar.dao.UserDao;
import com.shareanycar.dao.UserImageDao;
import com.shareanycar.providers.FactoryAmazonS3;
import com.shareanycar.providers.FactoryModelMapper;
import com.shareanycar.service.AmazonService;
import com.shareanycar.service.AuthService;
import com.shareanycar.service.CarService;
import com.shareanycar.service.ImageService;
import com.shareanycar.service.LocationService;
import com.shareanycar.service.MessageService;
import com.shareanycar.service.NotificationService;
import com.shareanycar.service.UserService;
import com.shareanycar.util.ContextUtil;
import com.shareanycar.util.MiscUtils;

public class CustomBinder extends AbstractBinder {

	@Override
	protected void configure() {

		bind(UserDao.class).to(UserDao.class).in(Singleton.class);
		bind(LocationDao.class).to(LocationDao.class).in(Singleton.class);
		bind(CarDao.class).to(CarDao.class).in(Singleton.class);
		bind(CarTypeDao.class).to(CarTypeDao.class).in(Singleton.class);
		bind(TransmissionTypeDao.class).to(TransmissionTypeDao.class).in(Singleton.class);
		bind(FuelTypeDao.class).to(FuelTypeDao.class).in(Singleton.class);
		bind(ManufacturerDao.class).to(ManufacturerDao.class).in(Singleton.class);
		bind(InsurerDao.class).to(InsurerDao.class).in(Singleton.class);
		bind(ImageDao.class).to(ImageDao.class).in(Singleton.class);
		bind(UserImageDao.class).to(UserImageDao.class).in(Singleton.class);
		bind(MessageDao.class).to(MessageDao.class).in(Singleton.class);
		bind(ExtDao.class).to(ExtDao.class).in(Singleton.class);

		bind(AmazonService.class).to(AmazonService.class).in(Singleton.class);
		bind(UserService.class).to(UserService.class).in(Singleton.class);
		bind(LocationService.class).to(LocationService.class).in(Singleton.class);
		bind(CarService.class).to(CarService.class).in(Singleton.class);
		bind(AuthService.class).to(AuthService.class).in(Singleton.class);
		bind(ImageService.class).to(ImageService.class).in(Singleton.class);
		bind(MessageService.class).to(MessageService.class).in(Singleton.class);
		bind(NotificationService.class).to(NotificationService.class).in(Singleton.class);

		bind(ContextUtil.class).to(ContextUtil.class).in(Singleton.class);
		bind(AppConfig.class).to(AppConfig.class).in(Singleton.class);
		bind(MiscUtils.class).to(MiscUtils.class).in(Singleton.class);
		bind(ConfigReader.class).to(ConfigReader.class).in(Singleton.class);
		bindFactory(FactoryModelMapper.class).to(ModelMapper.class).in(Singleton.class);
		bindFactory(FactoryAmazonS3.class).to(AmazonS3.class).in(Singleton.class);
		bind(LoggerFactory.getLogger(SimpleLogger.class)).to(Logger.class);

	}

}
