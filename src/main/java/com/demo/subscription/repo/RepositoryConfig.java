package com.demo.subscription.repo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.demo.subscription.model.MessageType;
import com.demo.subscription.model.Subscription;

@Configuration
public class RepositoryConfig extends RepositoryRestMvcConfiguration {

    @Override
    protected void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config) {
        config.exposeIdsFor(Subscription.class, MessageType.class);
    }
}