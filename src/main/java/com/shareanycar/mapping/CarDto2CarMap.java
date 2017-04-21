package com.shareanycar.mapping;

import org.modelmapper.PropertyMap;

import com.shareanycar.dto.CarDto;
import com.shareanycar.model.Car;

public class CarDto2CarMap extends PropertyMap<CarDto, Car>{

	@Override
	protected void configure() {
		skip().setUser(null);
		
	}

}
