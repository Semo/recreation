package com.y4d3.bootstrap;

import com.y4d3.domain.Customer;
import com.y4d3.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by semo on 21.12.16.
 */
@Component
public class BootupCustomers implements ApplicationListener<ContextRefreshedEvent> {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        loadCustomers();
    }

    private void loadCustomers() {
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

        customerService.saveOrUpdate(c1);

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

        customerService.saveOrUpdate(c2);

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

        customerService.saveOrUpdate(c3);

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

        customerService.saveOrUpdate(c4);
    }


}
