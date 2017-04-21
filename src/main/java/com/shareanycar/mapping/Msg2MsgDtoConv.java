package com.shareanycar.mapping;

import org.modelmapper.AbstractConverter;

import com.shareanycar.dto.MessageDto;
import com.shareanycar.model.Message;

public class Msg2MsgDtoConv  extends AbstractConverter<Message, MessageDto>{

	@Override
	protected MessageDto convert(Message source) {
		MessageDto msgDto = new MessageDto();
		
		msgDto.setFromUserId(source.getFromUser().getId());
		msgDto.setFromUserName( source.getFromUser().getFirstName());
		
		msgDto.setToUserId(source.getToUser().getId());
		msgDto.setToUserName(source.getToUser().getFirstName());
		
		return msgDto;
	}

}
