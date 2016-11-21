package com.demo.subscription.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.subscription.model.MessageType;
import com.demo.subscription.model.Subscription;
import com.demo.subscription.repo.MessageTypeRepository;
import com.demo.subscription.repo.SubscriptionRepository;
import com.demo.subscription.resource.SubscriptionResourse;

@RestController @RequestMapping("subscription")
@ExposesResourceFor(Subscription.class)
public class SubscriptionController {

	private final SubscriptionRepository repository;
	private final MessageTypeRepository messageTypeRepository; 
	
	@Autowired
	public SubscriptionController(final SubscriptionRepository repository, MessageTypeRepository messageTypeRepository) {
		this.repository = repository;
		this.messageTypeRepository = messageTypeRepository;
	}

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public SubscriptionResourse get(@PathVariable Long id) {
    	
    	Subscription subscription = repository.findOne(id);
    	SubscriptionResourse resource = new SubscriptionResourse(subscription);
    	
    	return resource;
    }
    
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<SubscriptionResourse> list() {
    	
    	List<Subscription> list = repository.findAll();
    	List<SubscriptionResourse> resources = new ArrayList<>();
    	
    	for (Subscription subscription : list) {
    		SubscriptionResourse resourse = new SubscriptionResourse(subscription);
    		resources.add(resourse);
    	}
    	
    	return resources;
    }

    @RequestMapping(method = RequestMethod.POST)
    public SubscriptionResourse add(@RequestBody List<Long> messageTypeIds) {
    	
    	Subscription subscription = new Subscription();
    	
    	for (Long id : messageTypeIds) {
    		MessageType messageType = messageTypeRepository.findOne(id);
    		subscription.addMessageType(messageType);
    	}
    	
    	repository.save(subscription);
    	
    	SubscriptionResourse resource = new SubscriptionResourse(subscription);

    	return resource;
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public SubscriptionResourse updateById(@PathVariable("id") Long id, @RequestBody List<Long> messageTypeIds) {
    	
    	Subscription subscription = repository.findOne(id);
    	subscription.removeSubscriptionMessageTypes();
    	repository.saveAndFlush(subscription);
    	
    	for (Long messageTypeId : messageTypeIds) {
    		MessageType messageType = messageTypeRepository.findOne(messageTypeId);
    		subscription.addMessageType(messageType);
    	}
    	
    	repository.save(subscription);
    	
    	SubscriptionResourse resource = new SubscriptionResourse(subscription);
    	
    	return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
    	repository.delete(id);
    }
}