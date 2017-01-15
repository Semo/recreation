package com.y4d3.services;

import com.y4d3.commands.CustomerForm;
import com.y4d3.domain.Customer;

/**
 * Created by semo on 17.12.16.
 */
public interface CustomerService extends CrudService<Customer> {

    Customer saveOrUpdateCustomerForm(CustomerForm customerForm);


}

