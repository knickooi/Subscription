package com.demo.subscription;

import org.h2.server.web.WebServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.demo.subscription.controller.SubscriptionController;
import com.demo.subscription.model.MessageType;
import com.demo.subscription.repo.MessageTypeRepository;
import com.demo.subscription.repo.SubscriptionRepository;

@SpringBootApplication
@ComponentScan(basePackageClasses = SubscriptionRepository.class)
@ComponentScan(basePackageClasses = SubscriptionController.class)
public class SubscriptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionApplication.class, args);
	}
	
	@Bean 
	CommandLineRunner init(final MessageTypeRepository repo) { 
		return (evt) -> {
			repo.save(new MessageType("News")); 
			repo.save(new MessageType("Sports"));
			repo.save(new MessageType("Movies")); 
			repo.save(new MessageType("Music"));
			repo.save(new MessageType("Tech"));
		}; 
	}
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
	    registration.addUrlMappings("/console/*");
	    return registration;
	}
	
	// JDBC: jdbc:h2:mem:AZ
}
