package com.demo.subscription.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Subscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(
	        mappedBy = "subscription",
	        fetch = FetchType.EAGER,
	        cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<SubscriptionMessageType> subscriptionMessageTypes;
	
	public Subscription() {}
	
	public Subscription(final List<MessageType> messageTypes) {
		setMessageTypes(messageTypes);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void increamentMessageCounter(final Long messageTypeId) {
		
		subscriptionMessageTypes.stream().filter(x -> x.getMessageType().getId().equals(messageTypeId)).forEach(x -> x.incrementCounter());
	}
	
	public void setMessageTypes(final List<MessageType> messageTypes) {
		
		for (MessageType type : messageTypes) {
			addMessageType(type);
		}
	}
	
	public void addMessageType(final MessageType messageType) {
		
		if (subscriptionMessageTypes == null) {
			subscriptionMessageTypes = new ArrayList<>();
		}
		
		SubscriptionMessageType subscriptionMessageType = new SubscriptionMessageType(this, messageType);
		
		subscriptionMessageTypes.add(subscriptionMessageType);
	}
	
	public void removeSubscriptionMessageType(final SubscriptionMessageType subscriptionMessageType) {
		subscriptionMessageTypes.remove(subscriptionMessageType);
		subscriptionMessageType.setSubscription(null);
		subscriptionMessageType.setMessageType(null);
	}
	
	public void removeSubscriptionMessageTypes() {
		
		Iterator<SubscriptionMessageType> iter = subscriptionMessageTypes.iterator();

		while (iter.hasNext()) {
			SubscriptionMessageType type = iter.next();
			iter.remove();
			type.setSubscription(null);
			type.setMessageType(null);
		}
	}

	public List<SubscriptionMessageType> getSubscriptionMessageTypes() {
		return subscriptionMessageTypes;
	}

	public void setSubscriptionMessageType(List<SubscriptionMessageType> subscriptionMessageTypes) {
		this.subscriptionMessageTypes = subscriptionMessageTypes;
	}
}