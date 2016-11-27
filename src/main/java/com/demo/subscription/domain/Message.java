package com.demo.subscription.domain;

import java.util.List;

import lombok.Data;

@Data
public class Message {
	
	private String description;
	private List<Long> messageTypeIds;

	public Message() {}
	
	public Message(final String description, List<Long> messageTypeIds) {
		this.description = description;
		this.messageTypeIds = messageTypeIds;
	}
}