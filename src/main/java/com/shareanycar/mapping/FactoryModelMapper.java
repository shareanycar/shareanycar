package com.shareanycar.mapping;

import javax.inject.Singleton;

import org.glassfish.hk2.api.Factory;
import org.modelmapper.ModelMapper;

import com.shareanycar.dto.CarDto;
import com.shareanycar.dto.MessageDto;
import com.shareanycar.dto.MessageInfoDto;
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
		
		modelMapper.getTypeMap(Car.class, CarDto.class).setPreConverter(new Car2CarDtoConv());
		modelMapper.getTypeMap(Message.class, MessageDto.class).setPreConverter(new Msg2MsgDtoConv());
		modelMapper.getTypeMap(Message.class, MessageInfoDto.class).setPreConverter(new Msg2MsgInfoDtoConv());
		
		return modelMapper;
	}

}
