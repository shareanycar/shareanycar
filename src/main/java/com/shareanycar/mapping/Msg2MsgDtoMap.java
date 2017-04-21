package com.shareanycar.mapping;

import org.modelmapper.PropertyMap;

import com.shareanycar.dto.MessageDto;
import com.shareanycar.model.Message;

public class Msg2MsgDtoMap  extends PropertyMap<Message, MessageDto>{

	@Override
	protected void configure() {
		skip().setFromUserName(null);
		skip().setToUserName(null);
		
	}

}
