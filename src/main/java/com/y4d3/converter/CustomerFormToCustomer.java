package com.y4d3.converter;

import com.y4d3.commands.CustomerForm;
import com.y4d3.domain.Address;
import com.y4d3.domain.Customer;
import com.y4d3.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by gandalf on 13.01.2017.
 */
@Component
public class CustomerFormToCustomer implements Converter<CustomerForm, Customer> {

    @Override
    public Customer convert(CustomerForm customerForm) {
        Customer customer = new Customer();
        customer.setUser(new User());
        customer.setBillingAddress(new Address());
        customer.setShippingAddress(new Address());
        customer.getUser().setId(customerForm.getUserId());
        customer.getUser().setVersion(customerForm.getUserVersion());
        customer.setId(customerForm.getCustomerId());
        customer.setVersion(customerForm.getCustomerVersion());
        customer.getUser().setUsername(customerForm.getUserName());
        customer.getUser().setPassword(customerForm.getPasswordText());
        customer.setFirstname(customerForm.getFirstName());
        customer.setLastname(customerForm.getLastName());
        customer.setEmail(customerForm.getEmail());
        customer.setPhonenumber(customerForm.getPhoneNumber());

        return customer;
    }
}
