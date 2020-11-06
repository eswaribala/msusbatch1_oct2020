package com.virtusa.banking.services;



import java.util.List;

import com.virtusa.banking.models.Customer;

public interface RemoteCustomerServiceClient {

    List<Customer> getAllCustomers();
}
