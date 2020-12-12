package org.gsu.pms.validator;

import org.gsu.pms.model.CustomerInfo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

// @Component: As a Bean.
@Component
public class CustomerInfoValidator implements Validator {

    // This Validator support CustomerInfo class.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == CustomerInfo.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Check the fields of CustomerInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customerForm.name");
    }

}
