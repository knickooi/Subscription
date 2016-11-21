package com.demo.subscription.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;

@Entity(name = "SUBSCRIPTION_MESSAGE_TYPE")
public class SubscriptionMessageType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="SUBSCRIPTION_ID")
	private Subscription subscription;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MESSAGE_TYPE_ID")
	private MessageType messageType;
	
	@Column(name = "COUNTER")
	private Integer counter = 0;

	public SubscriptionMessageType() {}
	
	public SubscriptionMessageType(final Subscription subscription, final MessageType messageType) {
		this.subscription = subscription;
		this.messageType = messageType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	 
	public void incrementCounter() {
		counter++;
	}
}
