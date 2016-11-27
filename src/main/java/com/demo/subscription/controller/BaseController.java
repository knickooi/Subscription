package com.demo.subscription.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.Link;

public abstract class BaseController<T, I extends Serializable> {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private JpaRepository<T, I> repository;

	@Autowired
	public BaseController(final JpaRepository<T, I> repository) {
		this.repository = repository;
	}
	
	protected Link getURI() {
		return linkTo(getClass()).withSelfRel();
	}
	
	protected Link getURI(final Object value) {
		return linkTo(getClass()).slash(value).withSelfRel();
	}
	
	protected JpaRepository<T, I> repository() {
		return repository;
	}
	
	protected void debug(final String text) {
		log.debug(text);
	}
	
	protected void info(final String text) {
		log.info(text);
	}
	
	protected void error(final String text) {
		log.error(text);
	}
}
