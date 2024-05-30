package com.example.BlogApplication.utils;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AboutValidation implements ConstraintValidator<AboutValidator,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.isBlank()){
            return false;
        }
        return true;
    }
}
