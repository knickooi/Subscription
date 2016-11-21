package com.demo.subscription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.subscription.repo.MessageTypeRepository;

@RestController @RequestMapping("messagetype")
public class MessageTypeController {
	
	private final MessageTypeRepository messageTypeRepository;

	@Autowired
	public MessageTypeController(final MessageTypeRepository messageTypeRepository) {
		this.messageTypeRepository = messageTypeRepository;
	}

}
