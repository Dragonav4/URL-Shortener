package org.example.s31722tpo10.Constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordComplexValidator implements ConstraintValidator<PasswordComplex,String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.length() < 10) {
            build(context, "{password.length}");
            return false;
        }

        int lowerCount = 0, upperCount = 0, digitCount = 0, specialCount = 0;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) lowerCount++;
            else if (Character.isUpperCase(c)) upperCount++;
            else if (Character.isDigit(c)) digitCount++;
            else specialCount++;
        }

        if (lowerCount < 1) {
            build(context, "{password.lowercase}");
            return false;
        }
        if (upperCount < 2) {
            build(context, "{password.uppercase}");
            return false;
        }
        if (digitCount < 3) {
            build(context, "{password.digits}");
            return false;
        }
        if (specialCount < 4) {
            build(context, "{password.special}");
            return false;
        }

        return true;
    }

    private void build(ConstraintValidatorContext context, String s) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(s).addConstraintViolation();
    }
}


