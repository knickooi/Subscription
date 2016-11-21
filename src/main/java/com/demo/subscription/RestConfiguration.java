package com.demo.subscription;

import javax.servlet.Filter;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class RestConfiguration {

   @Bean
   public Filter shallowEtagHeaderFilter() {
      // Note this filter does not improve application performance, as it requires the request to be fully processed to generate the ETag
      // It only saves bandwidth
      return new ShallowEtagHeaderFilter();
   }

   @Bean
   @Autowired
   public FilterRegistrationBean corsFilterRegistrationBean() {
      final FilterRegistrationBean registration = new FilterRegistrationBean();
      registration.setFilter(new CorsFilter());
      // Set Highest precedence: CORS filter has to be processed before any other filter, particularly any security filter
      registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
      return registration;
   }  
}