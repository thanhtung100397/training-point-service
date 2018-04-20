package com.kthdv.training_point.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringInValidator.class)
public @interface StringIn {
    String message() default "{com.worksfit.validation.value_in.StringInValidator.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String[] value();
    boolean acceptNull() default false;
}
