package com.demo.subscription.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.subscription.message.MessageSender;
import com.demo.subscription.model.Message;
import com.demo.subscription.model.Subscription;
import com.demo.subscription.repo.SubscriptionRepository;

@RestController @RequestMapping("message")
public class MessageController {
	
	private final SubscriptionRepository repository;
	
	@Autowired
	private MessageSender messageSender;
	
	@Autowired
	public MessageController(final SubscriptionRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(method = RequestMethod.POST)
    public void post(@RequestBody Message message) {
		
		for (Long id : message.getMessageTypeIds()) {
			List<Subscription> subscriptions = repository.findByMessageType(id);
			subscriptions.stream().forEach(s ->{
				s.increamentMessageCounter(id);
				repository.save(s);
			});
		}
		
		messageSender.sendMessage(message);
	}
}
