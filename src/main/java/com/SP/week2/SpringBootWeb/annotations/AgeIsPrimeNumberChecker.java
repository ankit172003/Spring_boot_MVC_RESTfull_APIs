package com.SP.week2.SpringBootWeb.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeIsPrimeNumberChecker implements ConstraintValidator<AgeIsPrimeNumber,Integer> {
    @Override
    public boolean isValid(Integer inputAge, ConstraintValidatorContext context) {
        if(inputAge == null) return false;
        boolean result = true;
        for(int i=2; i<=inputAge/2;i++){
            if(inputAge%i==0){
                result = false;
                break;
            }
        }

        return result;
    }
}
