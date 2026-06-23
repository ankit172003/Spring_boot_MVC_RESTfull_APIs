package com.SP.week2.SpringBootWeb.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {PasswordChecker.class})
public @interface PasswordCheck {

    String message() default "Password should Contain a UPPERCASE, lowercase, number, symbol";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
