package com.y4d3.services.map;

import com.y4d3.domain.Customer;
import com.y4d3.services.CustomerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by semo on 17.12.16.
 */
@Service
@Profile("map")
public class CustomerServiceImpl implements CustomerService {

    private Map<Integer, Customer> customers;

    @Override
    public List<Customer> listAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getById(Integer id) {
        return customers.get(id);
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        if (customer != null) {
            if (customer.getId() == null) {
                customer.setId(getNextKey());
            }
            customers.put(customer.getId(), customer);
            return customer;
        } else {
            throw new IllegalArgumentException("Customer must not be null!");
        }
    }

    @Override
    public void delete(Integer id) {
        customers.remove(id);
    }


    private Integer getNextKey() {
        return Collections.max(customers.keySet()) + 1;
    }

}