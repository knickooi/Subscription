package com.demo.subscription.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Subscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String description;
	
	@OneToMany(
	        mappedBy = "subscription",
	        fetch = FetchType.EAGER,
	        cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<SubscriptionMessageType> subscriptionMessageTypes;
	
	public Subscription() {}
	
	public Subscription(List<MessageType> messageTypes) {
		this(null, null, messageTypes);
	}
	
	public Subscription(final String name, final String description, List<MessageType> messageTypes) {
		this.name = name;
		this.description = description;
		
		setMessageTypes(messageTypes);
	}

	public void increamentMessageCounter(final Long messageTypeId) {
		getSubscriptionMessageTypes().stream()
									 .filter(x -> x.getMessageType().same(messageTypeId))
									 .forEach(x -> x.incrementCounter());
	}
	
	public void setMessageTypes(final @NotNull List<MessageType> messageTypes) {
		messageTypes.stream().forEach(this::addMessageType);
	}
	
	public List<MessageType> getMessageTypes() {
		return getSubscriptionMessageTypes().stream().map(m -> m.getMessageType()).collect(Collectors.toList());
	}
	
	public void addMessageType(final @NotNull MessageType messageType) {
		getSubscriptionMessageTypes().add(new SubscriptionMessageType(this, messageType));
	}
	
	public void removeSubscriptionMessageType(final @NotNull SubscriptionMessageType subscriptionMessageType) {
		subscriptionMessageType.detach();
		getSubscriptionMessageTypes().remove(subscriptionMessageType);
	}
	
	public void removeSubscriptionMessageTypes() {
		getSubscriptionMessageTypes().stream().forEach(m -> m.detach());
		getSubscriptionMessageTypes().clear();
	}

	public List<SubscriptionMessageType> getSubscriptionMessageTypes() {
		if (subscriptionMessageTypes == null) subscriptionMessageTypes = new ArrayList<>();
		return subscriptionMessageTypes;
	}

	public void setSubscriptionMessageType(List<SubscriptionMessageType> subscriptionMessageTypes) {
		this.subscriptionMessageTypes = subscriptionMessageTypes;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", MessageTypes=" + getMessageTypes() + "]";
	}
}