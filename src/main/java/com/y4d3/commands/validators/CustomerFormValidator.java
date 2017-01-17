package com.y4d3.commands.validators;

import com.y4d3.commands.CustomerForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by gandalf on 16.01.2017.
 */

@Component
public class CustomerFormValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerForm.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerForm customerForm = (CustomerForm) target;

        if (!customerForm.getPasswordText().contentEquals(customerForm.getPasswordText())) {
            errors.rejectValue("passwordTextConf", "PasswordsDontMatch.customerform.passwordTextConf", "Passwords don't match.");
        }
    }
}
