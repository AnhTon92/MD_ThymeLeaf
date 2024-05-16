package com.ra.employee_thymeleaf.validation;

import com.ra.employee_thymeleaf.dao.IEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class HandlePhoneExist implements ConstraintValidator<PhoneExist,String> {
    @Autowired
    private IEmployee employeeDao;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !employeeDao.existByPhone(s);
    }
}
