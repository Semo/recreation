package com.y4d3.converter;

import com.y4d3.commands.CustomerForm;
import com.y4d3.domain.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by gandalf on 16.01.2017.
 */
@Component
public class CustomerToCustomerForm implements Converter<Customer, CustomerForm> {


    @Override
    public CustomerForm convert(Customer customer) {
        CustomerForm customerForm = new CustomerForm();

        customerForm.setCustomerId(customer.getId());
        customerForm.setCustomerVersion(customer.getVersion());
        customerForm.setEmail(customer.getEmail());
        customerForm.setFirstName(customer.getFirstname());
        customerForm.setLastName(customer.getLastname());
        customerForm.setPhoneNumber(customer.getPhonenumber());
        customerForm.setUserId(customer.getUser().getId());
        customerForm.setUserName(customer.getUser().getUsername());
        customerForm.setUserVersion(customer.getUser().getVersion());

        return customerForm;
    }
}


