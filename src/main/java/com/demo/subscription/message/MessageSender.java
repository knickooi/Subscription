package com.demo.subscription.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.demo.subscription.domain.Message;

@Service
public class MessageSender {
	
	private static final String MESSAGE_QUEUE = "message.queue";

	private final JmsTemplate jmsTemplate;
	
	@Autowired
	public MessageSender(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void sendMessage(final Message message) {
		jmsTemplate.convertAndSend(MESSAGE_QUEUE, message.getDescription());
	}

}
