package org.example.s31722tpo10.Constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = UniqueUrlValidator.class)
    public @interface UniqueUrl{
        String message() default "{link.url.unique}";
        Class<?>[] groups() default {};
        Class<?extends Payload>[] payload() default {};
    }

