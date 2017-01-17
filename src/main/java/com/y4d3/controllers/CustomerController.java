package com.y4d3.controllers;

import com.y4d3.commands.CustomerForm;
import com.y4d3.domain.Customer;
import com.y4d3.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by semo on 17.12.16.
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/{id}")
    public String getCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "customer";
    }

    @RequestMapping("/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model) {
        Customer customer = customerService.getById(id);

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
        model.addAttribute("customer", customerForm);
        return "customer/customerform";
    }

    @RequestMapping("/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new CustomerForm());
        return "customerform";
    }

    @RequestMapping({"/list", "/"})
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.listAll());
        return "customers";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(CustomerForm customerForm) {
        Customer savedCustomer = customerService.saveOrUpdateCustomerForm(customerForm);
        return "redirect:/customer/show/" + savedCustomer.getId();
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }

}
