package com.shareanycar.mapping;

import javax.inject.Singleton;

import org.glassfish.hk2.api.Factory;
import org.modelmapper.ModelMapper;

import com.shareanycar.dto.CarDto;
import com.shareanycar.model.Car;

public class FactoryModelMapper implements Factory<ModelMapper>{

	@Override
	public void dispose(ModelMapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Singleton
	public ModelMapper provide() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new UserDtoMap());
		modelMapper.addMappings(new CarDtoMap());
		modelMapper.addMappings(new CarMap());
		
		modelMapper.getTypeMap(Car.class, CarDto.class).setPreConverter(new CarConverter());
		
		return modelMapper;
	}

}
