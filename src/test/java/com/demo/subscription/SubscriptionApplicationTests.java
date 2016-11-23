package com.demo.subscription;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.demo.subscription.model.MessageType;
import com.demo.subscription.resource.SubscriptionResource;
import com.fasterxml.jackson.annotation.JsonCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SubscriptionApplication.class)
@WebIntegrationTest
public class SubscriptionApplicationTests {

	@Test
	public void testSample() {
		RestTemplate restTemplate = new RestTemplate();
		MessageType type = restTemplate.getForObject("http://localhost:8080/messagetype/1", MessageType.class);
		
		System.out.println("****************");
		System.out.println(type.getName());
	}
	
//	@Test
//	public void testSubscription() {
//		RestTemplate restTemplate = new RestTemplate();
//		SubscriptionResourse response = restTemplate.postForEntity("http://localhost:8080/subscription", "[\"" +1 + "\",\"2\"," + "\"3\"]", SubscriptionResourse.class).getBody();
//		
//		System.out.println("****************");
//		System.out.println(response.getSubscriptionId());
//	}
	
	@Test
	public void contextLoads() {
	}

}
