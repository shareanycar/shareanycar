package com.shareanycar.mapping;

import org.modelmapper.PropertyMap;

import com.shareanycar.dto.UserDto;
import com.shareanycar.model.User;

public class UserDtoMap extends PropertyMap<UserDto, User>{

	@Override
	protected void configure() {
		skip().setUserImage(null);
		
	}

}
