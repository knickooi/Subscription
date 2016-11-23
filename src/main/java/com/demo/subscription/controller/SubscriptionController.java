package com.demo.subscription.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.subscription.model.Subscription;
import com.demo.subscription.repo.MessageTypeRepository;
import com.demo.subscription.repo.SubscriptionRepository;
import com.demo.subscription.resource.SubscriptionResource;

@RestController @RequestMapping("subscription")
@ExposesResourceFor(Subscription.class)
public class SubscriptionController extends BaseController<Subscription, Long> {
	
	private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	private final MessageTypeRepository messageTypeRepository; 
	
	@Autowired
	public SubscriptionController(final SubscriptionRepository repository, final MessageTypeRepository messageTypeRepository) {
		super(repository);
		this.messageTypeRepository = messageTypeRepository;
	}
	
	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public SubscriptionResource getById(@PathVariable Long id) {
		return SubscriptionResource.as(repository().findOne(id));
	}
    
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    protected List<SubscriptionResource> list() {
    	return repository().findAll().stream().map(SubscriptionResource::new).collect(Collectors.toList());
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public SubscriptionResource register(@RequestBody List<Long> messageTypeIds) {
    	
    	Subscription subscription = new Subscription();
    	messageTypeIds.stream().map(messageTypeRepository::findOne).forEach(subscription::addMessageType);
    	
    	repository().save(subscription);
    	
    	return SubscriptionResource.as(subscription);
    }
    
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public SubscriptionResource updateById(@PathVariable("id") Long id, @RequestBody List<Long> messageTypeIds) {
    	
    	Subscription subscription = repository().findOne(id);
    	subscription.removeSubscriptionMessageTypes();
    	repository().saveAndFlush(subscription);
    	
    	messageTypeIds.stream().map(messageTypeRepository::findOne).forEach(subscription::addMessageType);
    	
    	repository().save(subscription);
    	
    	return SubscriptionResource.as(subscription);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
    	repository().delete(id);
    }
}