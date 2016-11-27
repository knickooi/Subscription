package com.demo.subscription.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.demo.subscription.domain.Subscription;
import com.demo.subscription.repo.MessageTypeRepository;
import com.demo.subscription.repo.SubscriptionRepository;

import lombok.NonNull;

@Component
@Lazy
public class SubscriptionDriver {
	
	private final SubscriptionRepository repository;
	private final MessageTypeRepository messageTypeRepository;

	@Autowired
	public SubscriptionDriver(final SubscriptionRepository repository, final MessageTypeRepository messageTypeRepository) {
		this.repository = repository;
		this.messageTypeRepository = messageTypeRepository;
	}
	
	public Subscription findById(final @NonNull Long id) {
		
		Subscription subscription = repository.findOne(id);
		
		if (subscription == null) {
			throw new RuntimeException("Can't find subscription for id:" + id);
		}
		
		return subscription;
	}
	
	public List<Subscription> getAll() {
    	return repository.findAll();
    }

	public Subscription create(final List<Long> messageTypeIds) {
    	
    	Subscription subscription = new Subscription();
    	setMessageTypes(subscription, messageTypeIds);
    	
    	return repository.save(subscription);
    }
	
	public Subscription update(@NonNull Long id, List<Long> messageTypeIds) {
    	
    	Subscription subscription = findById(id);
    	subscription.removeSubscriptionMessageTypes();
    	repository.saveAndFlush(subscription);
    	
    	setMessageTypes(subscription, messageTypeIds);
    	
    	return repository.save(subscription);
    }
	
	public void delete(@NonNull Long id) {
    	repository.delete(id);
    }
	
	private void setMessageTypes(final Subscription subscription, final List<Long> messageTypeIds) {
		messageTypeIds.stream().map(messageTypeRepository::findOne).forEach(subscription::addMessageType);
	}
}
