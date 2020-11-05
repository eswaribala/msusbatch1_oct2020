package com.virtusa.banking.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.virtusa.banking.filters.BearerFilter;


@Configuration
public class FilterConfiguration {

	private FilterRegistrationBean filterRegistrationBean;	
	
	@Bean
	public FilterRegistrationBean instantiateFilterRegistrationBean()
	{
		filterRegistrationBean=new FilterRegistrationBean();		
		BearerFilter bearerFilter=new BearerFilter();
		filterRegistrationBean.setFilter(bearerFilter);
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
		
	}
	
}
