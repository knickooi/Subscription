package com.demo.subscription.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.subscription.domain.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	public final static String FIND_BY_MESSAGE_TYPE_ID = 
			"SELECT s " + 
            "FROM Subscription s JOIN s.subscriptionMessageTypes t JOIN t.messageType m " +
            "WHERE m.id = :messageId";
	
	@Query(FIND_BY_MESSAGE_TYPE_ID)
	public List<Subscription> findByMessageType(@Param("messageId") Long messageTypeId);
}
