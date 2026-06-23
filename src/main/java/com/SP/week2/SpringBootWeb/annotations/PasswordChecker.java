package com.SP.week2.SpringBootWeb.annotations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordChecker implements ConstraintValidator<PasswordCheck,String> {
    @Override
    public boolean isValid(String inputValue, ConstraintValidatorContext context) {
        if(inputValue == null && inputValue.length() <10 ) return false;

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for(char ch : inputValue.toCharArray()){
            if(Character.isUpperCase(ch)){
                hasUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowerCase = true;
            } else if(Character.isDigit(ch)){
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecial;
    }
}
