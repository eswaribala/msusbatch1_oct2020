package com.virtusa.banking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.banking.models.Customer;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    RemoteCustomerServiceClient remoteCustomerServiceClient;

    public List<Customer> getAllCustomers() {
        return remoteCustomerServiceClient.getAllCustomers();
    }
}
