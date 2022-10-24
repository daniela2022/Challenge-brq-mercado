package com.challengebrq.mercado.projetochallenge.entrypoint.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TextoEmBranco implements ConstraintValidator<TextoEmBrancoValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return value == null || value.trim().length() > 0;
    }
}
