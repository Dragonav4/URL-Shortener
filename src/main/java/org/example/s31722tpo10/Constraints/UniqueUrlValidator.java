package org.example.s31722tpo10.Constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.s31722tpo10.Interfaces.LinkRepository;
import org.springframework.stereotype.Component;

@Component
public class UniqueUrlValidator implements ConstraintValidator<UniqueUrl, String> {

    private final LinkRepository repository;

    public UniqueUrlValidator(LinkRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s!= null &&!repository.existsByTargetURL(s);
    }
}
