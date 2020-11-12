package com.virtusa.banking.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

public class RibbonConfiguration {

	@Autowired
	private IClientConfig clientConfig;
	
	@Bean
	public IPing pingRule(IClientConfig clientConfig)
	{
		
		return new PingUrl();
	}
	@Bean
	public IRule ribbonRule(IClientConfig clientConfig)
	{
		return new  WeightedResponseTimeRule();
	}
}
