package com.challengebrq.mercado.projetochallenge.entrypoint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD,PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = TextoEmBranco.class)
public @interface TextoEmBrancoValidation {

    String message() default "O valor do campo não pode estar em branco";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}
