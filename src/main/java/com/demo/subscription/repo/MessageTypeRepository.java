package com.demo.subscription.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.demo.subscription.model.MessageType;

@RepositoryRestResource(path = "messagetype", collectionResourceRel = "messagetype")
public interface MessageTypeRepository extends JpaRepository<MessageType, Long> {
}