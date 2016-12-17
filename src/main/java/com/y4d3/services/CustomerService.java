package com.y4d3.services;

import com.y4d3.domain.Customer;

import java.util.List;

/**
 * Created by semo on 17.12.16.
 */
public interface CustomerService {

    List<Customer> listAllCustomers();

    Customer getCustomerById(Integer id);

    Customer saveOrUpdateCustomer(Customer customer);

    void deleteCustomer(Integer id);
}
