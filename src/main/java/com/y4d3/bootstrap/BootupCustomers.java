package com.y4d3.bootstrap;

import com.y4d3.domain.Address;
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
        c1.setBillingAddress(new Address());
        c1.getBillingAddress().setAddressLine1("Einestrasse 1");
        c1.getBillingAddress().setAddressLine2("Einestrasse 12");
        c1.getBillingAddress().setCity("New York");
        c1.getBillingAddress().setZip("NY 9000");
        c1.getBillingAddress().setState("New York");
        c1.setShippingAddress(new Address());
        c1.getShippingAddress().setAddressLine1("Einestrasse 3");
        c1.getShippingAddress().setAddressLine2("Einestrasse 13");
        c1.getShippingAddress().setCity("Philadelphia");
        c1.getShippingAddress().setState("New York");
        c1.getShippingAddress().setZip("9500 PHI");
        c1.setEmail("mail1@example.org");
        c1.setPhonenumber("13251");

        customerService.saveOrUpdate(c1);

        Customer c2 = new Customer();
        c2.setId(2);
        c2.setFirstname("Ray");
        c2.setLastname("Stantz");
        c2.setBillingAddress(new Address());
        c2.getBillingAddress().setAddressLine1("Einestrasse 1");
        c2.getBillingAddress().setAddressLine2("Einestrasse 12");
        c2.getBillingAddress().setCity("2132");
        c2.getBillingAddress().setZip("NY 9000");
        c2.getBillingAddress().setState("New York");
        c2.setShippingAddress(new Address());
        c2.getShippingAddress().setAddressLine1("Zweitestrasse 4");
        c2.getShippingAddress().setAddressLine2("Zweitestrasse 14");
        c2.getShippingAddress().setCity("Philadelhia");
        c2.getShippingAddress().setState("New York");
        c2.getShippingAddress().setZip("9500 PHI");
        c2.setEmail("mail2@example.org");
        c2.setPhonenumber("13251");

        customerService.saveOrUpdate(c2);

        Customer c3 = new Customer();
        c3.setId(3);
        c3.setFirstname("Egon");
        c3.setLastname("Spengler");
        c3.setBillingAddress(new Address());
        c3.getBillingAddress().setAddressLine1("Einestrasse 1");
        c3.getBillingAddress().setAddressLine2("Einelstrasse 12");
        c3.getBillingAddress().setCity("2132");
        c3.getBillingAddress().setZip("NY 9000");
        c3.getBillingAddress().setState("New York");
        c3.setShippingAddress(new Address());
        c3.getShippingAddress().setAddressLine1("Drittestrasse 5");
        c3.getShippingAddress().setAddressLine2("Drittestrasse 15");
        c3.getShippingAddress().setCity("Philadelphia");
        c3.getShippingAddress().setState("New York");
        c3.getShippingAddress().setZip("9500 PHI");
        c3.setEmail("mail3@example.org");
        c3.setPhonenumber("13251");

        customerService.saveOrUpdate(c3);

        Customer c4 = new Customer();
        c4.setId(4);
        c4.setFirstname("Winston");
        c4.setLastname("Zeddemore");
        c4.setBillingAddress(new Address());
        c4.getBillingAddress().setAddressLine1("Einestrasse 1");
        c4.getBillingAddress().setAddressLine2("Einestrasse 12");
        c4.getBillingAddress().setCity("2132");
        c4.getBillingAddress().setState("New York");
        c4.getBillingAddress().setZip("NY 9000");
        c4.setEmail("mail1@example.org");
        c4.setPhonenumber("13251");
        c4.setShippingAddress(new Address());
        c4.getShippingAddress().setAddressLine1("Viertestrasse 6");
        c4.getShippingAddress().setAddressLine2("Viertestrasse 16");
        c4.getShippingAddress().setCity("Philadelphia");
        c4.getShippingAddress().setState("New York");
        c4.getShippingAddress().setZip("9500 PHI");

        customerService.saveOrUpdate(c4);
    }


}
