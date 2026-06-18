package com.SP.week2.SpringBootWeb.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Constraint(validatedBy = AgeIsPrimeNumberChecker.class)
public @interface AgeIsPrimeNumber {

    String message() default "Age is not a prime number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
