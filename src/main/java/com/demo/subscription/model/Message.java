package com.demo.subscription.model;

import java.util.List;

public class Message {
	
	private String description;
	private List<Long> messageTypeIds;

	public Message() {}
	
	public Message(final String description, List<Long> messageTypeIds) {
		this.description = description;
		this.messageTypeIds = messageTypeIds;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getMessageTypeIds() {
		return messageTypeIds;
	}

	public void setMessageTypeIds(List<Long> messageTypeIds) {
		this.messageTypeIds = messageTypeIds;
	}

	@Override
	public String toString() {
		return "Message [description=" + description + ", messageTypeIds=" + messageTypeIds + "]";
	}
}
