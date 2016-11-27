package com.demo.subscription.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class MessageType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	public MessageType() {}
	
	public MessageType(final String name) {
		this.name = name;
	}
	
	public boolean same(final @NonNull Long id) {
		return this.id.equals(id);
	}
}

