package com.demo.subscription;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.demo.subscription.controller.SubscriptionController;
import com.demo.subscription.domain.MessageType;
import com.demo.subscription.repo.MessageTypeRepository;
import com.demo.subscription.repo.SubscriptionRepository;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
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
	
//	@Bean
//	public ServletRegistrationBean h2servletRegistration() {
//	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
//	    registration.addUrlMappings("/console/*");
//	    return registration;
//	}
	
	// JDBC: jdbc:h2:mem:AZ
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build()
          .pathMapping("/");
    }
}
