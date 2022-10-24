package com.challengebrq.mercado.projetochallenge.entrypoint.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class IntegerEmBranco implements ConstraintValidator<IntegerEmBrancoValidation, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return !Objects.isNull(value);
    }
}
