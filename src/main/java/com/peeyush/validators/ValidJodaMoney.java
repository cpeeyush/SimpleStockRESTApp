package com.peeyush.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom Annotation for Validating the API input for a valid Joda Money.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { MoneyValidator.class })
public @interface ValidJodaMoney {
  String message() default "Not a valid money format. Valid money is e.g. USD 23.47";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
