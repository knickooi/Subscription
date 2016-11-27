package com.demo.subscription.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.demo.subscription.controller.MessageTypeController;
import com.demo.subscription.domain.MessageType;

public class MessageTypeResource extends ResourceSupport {
	
	private MessageType messageType;
	private int messageCount;

	public MessageTypeResource(final MessageType messageType, int messageCount) {
		this.messageType = messageType;
		this.messageCount = messageCount;
		
		Link detail = linkTo(MessageTypeController.class).slash(getTypeId()).withSelfRel();
		add(detail);
	}
	
	public Long getTypeId() {
		return messageType.getId();
	}
	
	public String getName() {
		return messageType.getName();
	}
	
	public int getMessageCount() {
		return messageCount;
	}
	
	public static MessageTypeResource as(final MessageType messageType) {
		return new MessageTypeResource(messageType, 0);
	}
}
