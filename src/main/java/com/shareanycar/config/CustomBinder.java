package com.shareanycar.config;



import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.SimpleLogger;

import com.shareanycar.dao.BookingDao;
import com.shareanycar.dao.CarDao;
import com.shareanycar.dao.CarTypeDao;
import com.shareanycar.dao.CustomerDao;
import com.shareanycar.dao.ExtDao;
import com.shareanycar.dao.FuelTypeDao;
import com.shareanycar.dao.ImageDao;
import com.shareanycar.dao.LocationDao;
import com.shareanycar.dao.MessageDao;
import com.shareanycar.dao.OwnerDao;
import com.shareanycar.dao.TransmissionTypeDao;
import com.shareanycar.service.AuthService;
import com.shareanycar.service.BookingService;
import com.shareanycar.service.CarService;
import com.shareanycar.service.CustomerService;
import com.shareanycar.service.ImageService;
import com.shareanycar.service.LocationService;
import com.shareanycar.service.MessageService;
import com.shareanycar.service.OwnerService;
import com.shareanycar.util.ContextUtil;
import com.shareanycar.util.MiscUtils;

public class CustomBinder extends AbstractBinder{

	@Override
	protected void configure() {
		
		bind(OwnerDao.class).to(OwnerDao.class).in(Singleton.class);
		bind(CustomerDao.class).to(CustomerDao.class).in(Singleton.class);
		bind(LocationDao.class).to(LocationDao.class).in(Singleton.class);
		bind(BookingDao.class).to(BookingDao.class).in(Singleton.class);
		bind(CarDao.class).to(CarDao.class).in(Singleton.class);
		bind(CarTypeDao.class).to(CarTypeDao.class).in(Singleton.class);
		bind(TransmissionTypeDao.class).to(TransmissionTypeDao.class).in(Singleton.class);
		bind(FuelTypeDao.class).to(FuelTypeDao.class).in(Singleton.class);
		
		bind(MessageDao.class).to(MessageDao.class).in(Singleton.class);
		bind(ImageDao.class).to(ImageDao.class).in(Singleton.class);
		bind(ExtDao.class).to(ExtDao.class).in(Singleton.class);

		
		bind(OwnerService.class).to(OwnerService.class).in(Singleton.class);		
		bind(CustomerService.class).to(CustomerService.class).in(Singleton.class);
		bind(LocationService.class).to(LocationService.class).in(Singleton.class);
		bind(BookingService.class).to(BookingService.class).in(Singleton.class);
		bind(CarService.class).to(CarService.class).in(Singleton.class);
		bind(MessageService.class).to(MessageService.class).in(Singleton.class);
		bind(AuthService.class).to(AuthService.class).in(Singleton.class);
		
		bind(ImageService.class).to(ImageService.class).in(Singleton.class);
		bind(ContextUtil.class).to(ContextUtil.class).in(Singleton.class);
		bind(AppConfig.class).to(AppConfig.class).in(Singleton.class);
		bind(MiscUtils.class).to(MiscUtils.class).in(Singleton.class);

		bind(ModelMapper.class).to(ModelMapper.class).in(Singleton.class);
		bind(LoggerFactory.getLogger(SimpleLogger.class)).to(Logger.class);

		
		
	}

}
