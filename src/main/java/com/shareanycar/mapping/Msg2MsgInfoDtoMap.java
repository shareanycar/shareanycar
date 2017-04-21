package com.shareanycar.mapping;

import org.modelmapper.PropertyMap;

import com.shareanycar.dto.MessageInfoDto;
import com.shareanycar.model.Message;

public class Msg2MsgInfoDtoMap  extends PropertyMap<Message, MessageInfoDto> {

	@Override
	protected void configure() {
		skip().setFromUserName(null);
		skip().setToUserName(null);
		
	}

}
