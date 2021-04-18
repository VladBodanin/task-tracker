package com.test.tracker.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
  private List<String> acceptedValues;

  public void initialize(ValueOfEnum constraint) {
    acceptedValues =
        Stream.of(constraint.enumClass().getEnumConstants())
            .map(Enum::name)
            .collect(Collectors.toList());
  }

  public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

    if (value == null) {
      return true;
    }
    return acceptedValues.contains(value.toString().toUpperCase());
  }
}
