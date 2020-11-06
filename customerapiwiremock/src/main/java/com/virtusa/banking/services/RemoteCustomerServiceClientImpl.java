package com.virtusa.banking.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.virtusa.banking.models.Customer;

import java.util.List;

@Service
public class RemoteCustomerServiceClientImpl implements RemoteCustomerServiceClient{

    @Value(value = "${remote.url}")
    private String remoteServiceURL;

    private final RestTemplate restTemplate;

    @Autowired
    public RemoteCustomerServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Customer> getAllCustomers() {
        ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(remoteServiceURL, HttpMethod.GET,null,
                new ParameterizedTypeReference<List<Customer>>(){});
        return responseEntity.getBody();
    }
}
