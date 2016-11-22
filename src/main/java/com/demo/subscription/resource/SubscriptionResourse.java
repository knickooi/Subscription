package com.demo.subscription.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.demo.subscription.controller.SubscriptionController;
import com.demo.subscription.model.Subscription;

public class SubscriptionResourse extends ResourceSupport {

	private Subscription subscription;
	 
	public SubscriptionResourse(final Subscription subscription) {
		this.subscription = subscription;
		
		Link detail = linkTo(SubscriptionController.class).slash(getSubscriptionId()).withSelfRel();
		add(detail);
	}
	 
	public Long getSubscriptionId() {
		return subscription.getId();
	}
	
	public List<MessageTypeResource> getMessageTypes() {
	
		List<MessageTypeResource> messageTypes = subscription.getSubscriptionMessageTypes()
												 .stream()
												 .map(s -> new MessageTypeResource(s.getMessageType(), s.getCounter()))
												 .collect(Collectors.toList());
		return messageTypes;
	}
}