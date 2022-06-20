package com.example.bookManager.service;

import com.example.bookManager.domain.CustomerDetail;
import com.example.bookManager.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void CreateNewCustomer(String username)
    {
        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail.setUsername(username);
        customerRepository.save(customerDetail);
    }

    public void DeleteCustomer(String username)
    {

    }

    public CustomerDetail getCustomerDetailByUsername(String username)
    {
        return customerRepository.findByUsername(username);
    }
}
