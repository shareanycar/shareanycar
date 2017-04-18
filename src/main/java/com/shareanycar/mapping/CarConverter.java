package com.shareanycar.mapping;

import org.modelmapper.AbstractConverter;

import com.shareanycar.dto.CarDto;
import com.shareanycar.model.Car;
import com.shareanycar.model.Image;

public class CarConverter extends AbstractConverter<Car, CarDto>{
	
	@Override
	protected CarDto convert(Car source) {
		CarDto carDto = new CarDto();
		
		for(Image image : source.getImages()){
			if(image.isMain()){
				carDto.setImageUrlSmall(image.getUrlSmall());
				carDto.setImageUrlMedium(image.getUrlMedium());
				carDto.setImageUrlLarge(image.getUrlLarge());
			}
		}
		carDto.setUserFirstName(source.getUser().getFirstName());
		carDto.setUserId(source.getUser().getId());
		
		return carDto;
	}

}
