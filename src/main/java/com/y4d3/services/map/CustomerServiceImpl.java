package com.y4d3.services.map;

import com.y4d3.commands.CustomerForm;
import com.y4d3.converter.CustomerFormToCustomer;
import com.y4d3.domain.Customer;
import com.y4d3.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CustomerFormToCustomer customerFormToCustomer;

    @Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
    }

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

    @Override
    public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
        Customer newCustomer = customerFormToCustomer.convert(customerForm);

        if(newCustomer.getUser().getId() != null){
            Customer existingCustomer = getById(newCustomer.getId());

            newCustomer.getUser().setIsActive(existingCustomer.getUser().getIsActive());
        }

        return saveOrUpdate(newCustomer);



    }
}