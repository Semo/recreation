package com.y4d3.services;

import com.y4d3.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by semo on 17.12.16.
 */
@Service
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
            if (customer.getId() == null) {
                customer.setId(getNextKey());
            }
            customers.put(customer.getId(), customer);
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

        Customer c1 = new Customer();
        c1.setId(1);
        c1.setFirstname("Peter");
        c1.setLastname("Venkman");
        c1.setAddressOne("Mumbelstrasse 1");
        c1.setAddress_two("Mumbelstrasse 12");
        c1.setCity("2132");
        c1.setZip("New York");
        c1.setEmail("mail1@example.org");
        c1.setPhonenumber("13251");
        c1.setState("New York");

        customers.put(1, c1);

        Customer c2 = new Customer();
        c2.setId(2);
        c2.setFirstname("Ray");
        c2.setLastname("Stantz");
        c2.setAddressOne("Mumbelstrasse 1");
        c2.setAddress_two("Mumbelstrasse 12");
        c2.setCity("2132");
        c2.setZip("New York");
        c2.setEmail("mail2@example.org");
        c2.setPhonenumber("13251");
        c2.setState("New York");

        customers.put(2, c2);

        Customer c3 = new Customer();
        c3.setId(3);
        c3.setFirstname("Egon");
        c3.setLastname("Spengler");
        c3.setAddressOne("Mumbelstrasse 1");
        c3.setAddress_two("Mumbelstrasse 12");
        c3.setCity("2132");
        c3.setZip("New York");
        c3.setEmail("mail3@example.org");
        c3.setPhonenumber("13251");
        c3.setState("New York");

        customers.put(3, c3);

        Customer c4 = new Customer();
        c4.setId(4);
        c4.setFirstname("Winston");
        c4.setLastname("Zeddemore");
        c4.setAddressOne("Mumbelstrasse 1");
        c4.setAddress_two("Mumbelstrasse 12");
        c4.setCity("2132");
        c4.setZip("New York");
        c4.setEmail("mail1@example.org");
        c4.setPhonenumber("13251");
        c4.setState("New York");

        customers.put(4, c4);

    }
}