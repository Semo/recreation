package com.y4d3.controllers;

import com.y4d3.commands.CustomerForm;
import com.y4d3.domain.Address;
import com.y4d3.domain.Customer;
import com.y4d3.domain.User;
import com.y4d3.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by semo on 20.12.16.
 */
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        //specific Mockito interaction, tell stub to return list of products
        when(customerService.listAll()).thenReturn((List) customers); //need to strip generics to keep Mockito happy.

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers"))
                .andExpect(model().attribute("customers", hasSize(2)));

    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;

        //Tell Mockito stub to return new product for ID 1
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNewCustomer() throws Exception {
        Integer id = 1;

        //should not call service
        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerform"))
                .andExpect(model().attribute("customer", instanceOf(CustomerForm.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id = 1;

        //Tell Mockito stub to return new product for ID 1
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));

        verify(customerService, times(1)).delete(id);
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        Customer returnCustomer = new Customer();
        String firstName = "Joe";
        String lastName = "Doe";
        String addressLineUno = "9855 Simpleton Ave";
        String addressLine2 = "around the corner";
        String city = "Simpleton";
        String state = "Montana";
        String zipCode = "02254";
        String email = "joedoe@mailserver.com";
        String phoneNumber = "+14 / 222 555 444";
        String username = "mweston";
        String password = "password";

        returnCustomer.setId(id);
        returnCustomer.setFirstname(firstName);
        returnCustomer.setLastname(lastName);
        returnCustomer.setBillingAddress(new Address());
        returnCustomer.getBillingAddress().setAddressLine1(addressLineUno);
        returnCustomer.getBillingAddress().setAddressLine2(addressLine2);
        returnCustomer.getBillingAddress().setCity(city);
        returnCustomer.getBillingAddress().setState(state);
        returnCustomer.getBillingAddress().setZip(zipCode);
        returnCustomer.setEmail(email);
        returnCustomer.setPhonenumber(phoneNumber);
        returnCustomer.setUser(new User());
        returnCustomer.getUser().setUsername(username);
        returnCustomer.getUser().setPassword(password);

        when(customerService.saveOrUpdateCustomerForm(Matchers.<CustomerForm>any())).thenReturn(returnCustomer);
        when(customerService.getById(Matchers.<Integer>any())).thenReturn(returnCustomer);

        mockMvc.perform(post("/customer")
                .param("customerId", "1")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("userName", username)
                .param("passwordText", password)
                .param("passwordTextConf", password)
                .param("shippingAddress.addressLine1", addressLineUno)
                .param("shippingAddress.addressLine2", addressLine2)
                .param("shippingAddress.city", city)
                .param("shippingAddress.state", state)
                .param("shippingAddress.zipCode", zipCode)
                .param("email", email)
                .param("phoneNumber", phoneNumber))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/show/1"));

        ArgumentCaptor<CustomerForm> customerCaptor = ArgumentCaptor.forClass(CustomerForm.class);
        verify(customerService).saveOrUpdateCustomerForm(customerCaptor.capture());

        CustomerForm boundCustomer = customerCaptor.getValue();

        assertEquals(id, boundCustomer.getCustomerId());
        assertEquals(firstName, boundCustomer.getFirstName());
        assertEquals(lastName, boundCustomer.getLastName());
        assertEquals(email, boundCustomer.getEmail());
        assertEquals(phoneNumber, boundCustomer.getPhoneNumber());
    }

}
