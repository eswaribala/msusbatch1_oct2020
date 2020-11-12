package com.virtusa.banking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RibbonController {

	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Value("${serviceId}")
	private String serviceId;
	
	@GetMapping("/tests")
	public ResponseEntity<?> testLoadBalancer()
	{
		
		// (Need!!) eureka.client.fetchRegistry=true
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
 
        if (instances == null || instances.isEmpty()) {
        	return ResponseEntity.ok("No instances for service: " + serviceId);
          
        }
       // else
        //	return ResponseEntity.ok(instances);
        
        ServiceInstance serviceInstance = this.loadBalancerClient.choose(serviceId);
        return ResponseEntity.ok(serviceInstance);
        
		
	}
	
	
}
