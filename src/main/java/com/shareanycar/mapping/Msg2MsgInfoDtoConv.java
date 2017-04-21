package com.shareanycar.mapping;

import org.modelmapper.AbstractConverter;

import com.shareanycar.dto.MessageInfoDto;
import com.shareanycar.model.Message;

public class Msg2MsgInfoDtoConv  extends AbstractConverter<Message, MessageInfoDto> {

	@Override
	protected MessageInfoDto convert(Message source) {
		MessageInfoDto msgInfoDto = new MessageInfoDto();
		
		msgInfoDto.setFromUserId(source.getFromUser().getId());
		msgInfoDto.setFromUserName(source.getFromUser().getFirstName());
		
		msgInfoDto.setToUserId(source.getFromUser().getId());
		msgInfoDto.setToUserName(source.getFromUser().getFirstName());
		
		return msgInfoDto;
	}

}
