package com.peeyush.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang.StringUtils;
import org.joda.money.Money;

/***
 * Custom Validator to validate a String as per Joda Money Format.
 */
public class MoneyValidator implements ConstraintValidator<ValidJodaMoney, String> {

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (StringUtils.isBlank(s)) return false;

    try {
      Money money = Money.parse(s);
      if (null != money) {
        return true;
      }
    } catch (Exception e) {
      return false;
    }
    return false;
  }
}
