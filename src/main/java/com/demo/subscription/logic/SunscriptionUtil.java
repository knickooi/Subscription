package com.demo.subscription.logic;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.subscription.model.Subscription;
import com.demo.subscription.repo.SubscriptionRepository;

public class SunscriptionUtil {
	
	private final SubscriptionRepository repository;

	@Autowired
	public SunscriptionUtil(final SubscriptionRepository repository) {
		this.repository = repository;
	}
	
	public Subscription fundById(final Long id) {
		
		Subscription subscription = repository.findOne(id);
		
		if (subscription == null) {
			throw new RuntimeException("Can't find subscription for id:" + id);
		}
		
		return subscription;
	}

}
