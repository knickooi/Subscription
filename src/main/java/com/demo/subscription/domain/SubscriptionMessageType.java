package com.demo.subscription.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

import javax.persistence.GenerationType;

@Entity(name = "SUBSCRIPTION_MESSAGE_TYPE")
@Data
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
	
	public void detach() {
		this.subscription = null;
		this.messageType = null;
	}

	public void incrementCounter() {
		counter++;
	}
}
