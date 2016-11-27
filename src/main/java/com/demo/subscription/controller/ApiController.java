package com.demo.subscription.controller;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface ApiController<T> {

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResourceSupport get(@PathVariable T id);
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<? extends ResourceSupport> getAll();
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResourceSupport create(@RequestBody T[] ids);
	
	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResourceSupport update(@PathVariable("id") T id, @RequestBody T[] ids);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable T id);
}
