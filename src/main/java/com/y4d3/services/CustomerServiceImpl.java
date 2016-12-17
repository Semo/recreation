package com.y4d3.services;

import com.y4d3.domain.Customer;

import java.util.*;

/**
 * Created by semo on 17.12.16.
 */
public class CustomerServiceImpl implements CustomerService {

    private Map<Integer, Customer> customers;

    public CustomerServiceImpl() {
        loadAllCustomers();
    }

    @Override
    public List<Customer> listAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customers.get(id);
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
        if (customer != null) {
            if (customer.getCustomerId() == null) {
                customer.setCustomerId(getNextKey());
            }
            customers.put(customer.getCustomerId(), customer);
            return customer;
        } else {
            throw new IllegalArgumentException("Customer must not be null!");
        }
    }

    private Integer getNextKey() {
        return Collections.max(customers.keySet()) + 1;
    }

    @Override
    public void deleteCustomer(Integer id) {
        customers.remove(id);
    }

    private void loadAllCustomers() {
        customers = new HashMap<>();


    }
}