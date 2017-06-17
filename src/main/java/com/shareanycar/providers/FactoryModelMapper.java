package com.shareanycar.providers;

import javax.inject.Singleton;

import org.glassfish.hk2.api.Factory;
import org.modelmapper.ModelMapper;

import com.shareanycar.dto.BookingDto;
import com.shareanycar.dto.CarDto;
import com.shareanycar.dto.MessageDto;
import com.shareanycar.dto.MessageInfoDto;
import com.shareanycar.mapping.Booking2BookingDtoConv;
import com.shareanycar.mapping.Booking2BookingDtoMap;
import com.shareanycar.mapping.BookingDto2BookingConv;
import com.shareanycar.mapping.BookingDto2BookingMap;
import com.shareanycar.mapping.Car2CarDtoConv;
import com.shareanycar.mapping.Car2CarDtoMap;
import com.shareanycar.mapping.CarDto2CarMap;
import com.shareanycar.mapping.Msg2MsgDtoConv;
import com.shareanycar.mapping.Msg2MsgDtoMap;
import com.shareanycar.mapping.Msg2MsgInfoDtoConv;
import com.shareanycar.mapping.Msg2MsgInfoDtoMap;
import com.shareanycar.mapping.UserDto2UserMap;
import com.shareanycar.model.Booking;
import com.shareanycar.model.Car;
import com.shareanycar.model.Message;

public class FactoryModelMapper implements Factory<ModelMapper>{

	@Override
	public void dispose(ModelMapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Singleton
	public ModelMapper provide() {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.addMappings(new UserDto2UserMap());
		modelMapper.addMappings(new CarDto2CarMap());
		modelMapper.addMappings(new Car2CarDtoMap());		
		modelMapper.addMappings(new Msg2MsgDtoMap());
		modelMapper.addMappings(new Msg2MsgInfoDtoMap());
		modelMapper.addMappings(new BookingDto2BookingMap());
		modelMapper.addMappings(new Booking2BookingDtoMap());

		
		modelMapper.getTypeMap(Car.class, CarDto.class).setPreConverter(new Car2CarDtoConv());
		modelMapper.getTypeMap(Message.class, MessageDto.class).setPreConverter(new Msg2MsgDtoConv());
		modelMapper.getTypeMap(Message.class, MessageInfoDto.class).setPreConverter(new Msg2MsgInfoDtoConv());
		modelMapper.getTypeMap(BookingDto.class, Booking.class).setPreConverter(new BookingDto2BookingConv());
		modelMapper.getTypeMap(Booking.class, BookingDto.class).setPreConverter(new Booking2BookingDtoConv());
		
		return modelMapper;
	}

}
