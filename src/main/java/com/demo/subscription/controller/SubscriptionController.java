package com.demo.subscription.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.subscription.domain.Subscription;
import com.demo.subscription.logic.SubscriptionDriver;
import com.demo.subscription.resource.SubscriptionResource;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController @RequestMapping("subscription")
@ExposesResourceFor(Subscription.class)
public class SubscriptionController {
	
	private SubscriptionDriver driver;
	
	@Autowired
	public SubscriptionController(final SubscriptionDriver driver) {
		this.driver = driver;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SubscriptionResource get(@PathVariable Long id) {
		log.debug("Getting Subscription with id " + id);
		
		val subscription = driver.findById(id);
   	 
        if (subscription == null) {
        	val message = "Unable to find. Subscription with id " + id + " not found";
            log.error(message);
            throw new RuntimeException(message);
        }
        
		return SubscriptionResource.as(subscription);
	}
    
	@RequestMapping(method = RequestMethod.GET)
	public List<SubscriptionResource> getAll() {
		log.debug("Getting all Subscriptions");
    	return SubscriptionResource.as(driver.getAll());
    }

	@RequestMapping(method = RequestMethod.POST)
    public SubscriptionResource create(@RequestBody List<Long> messageTypeIds) {
		log.debug("Creating Subscription");
		return SubscriptionResource.as(driver.create(messageTypeIds));
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public SubscriptionResource update(@PathVariable("id") Long id, @RequestBody List<Long> messageTypeIds) {
    	log.debug("Updating Subscription with id " + id);
    	
    	val subscription = driver.findById(id);
    	 
        if (subscription == null) {
        	val message = "Unable to update. Subscription with id " + id + " not found";
            log.error(message);
            throw new RuntimeException(message);
        }
    	return SubscriptionResource.as(driver.update(id, messageTypeIds));
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
    	log.debug("Deleting Subscription with id " + id);
    	
    	val subscription = driver.findById(id);
    	 
        if (subscription == null) {
        	val message = "Unable to delete. Subscription with id " + id + " not found";
            log.error(message);
            throw new RuntimeException(message);
        }
        
        driver.delete(id);
    }
}