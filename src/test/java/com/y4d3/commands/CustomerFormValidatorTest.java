package com.y4d3.commands;

import com.y4d3.commands.validators.CustomerFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Created by gandalf on 16.01.2017.
 */

public class CustomerFormValidatorTest {

    private Validator validator;
    private CustomerForm customerForm;
    private Errors errors;

    @Before
    public void setUp() {
        validator = new CustomerFormValidator();
        customerForm = new CustomerForm();
        errors = new BeanPropertyBindingResult(customerForm, "customerForm");
    }

    @Test
    public void testNoErrors() throws Exception {
        customerForm.setPasswordText("password");
        customerForm.setPasswordTextConf("password");

        validator.validate(customerForm, errors);

        assert errors.hasErrors() == false;
    }

    @Test
    public void testHasErrors() throws Exception {
        customerForm.setPasswordText("password");
        customerForm.setPasswordTextConf("asdf");

        validator.validate(customerForm, errors);

        if (errors.hasErrors() == true) {
            assert true;
        } else {
            throw new AssertionError("Password validation must fail, but it does not. Please check me.");
        }
    }
}
