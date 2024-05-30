package com.example.BlogApplication.utils;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AboutValidation.class)
public @interface AboutValidator {

    String message() default "please provide valid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
