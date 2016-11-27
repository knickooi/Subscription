package com.demo.subscription.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.subscription.domain.Subscription;
import com.demo.subscription.logic.SubscriptionDriver;
import com.demo.subscription.resource.SubscriptionResource;

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
		return SubscriptionResource.as(driver.findById(id));
	}
    
	@RequestMapping(method = RequestMethod.GET)
	public List<SubscriptionResource> getAll() {
    	return SubscriptionResource.as(driver.getAll());
    }

	@RequestMapping(method = RequestMethod.POST)
    public SubscriptionResource create(@RequestBody List<Long> messageTypeIds) {
		return SubscriptionResource.as(driver.create(messageTypeIds));
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public SubscriptionResource update(@PathVariable("id") Long id, @RequestBody List<Long> messageTypeIds) {
    	return SubscriptionResource.as(driver.update(id, messageTypeIds));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
    	driver.delete(id);
    }
}