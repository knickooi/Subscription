package com.demo.subscription.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	public MessageListener() {
	}
	
	@JmsListener(destination = "message.queue")
	public void receiveMessage(final String message) {
		System.out.println(message);
	}

}
