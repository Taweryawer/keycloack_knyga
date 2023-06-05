package validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinimalLengthIfNotEmptyValidator implements ConstraintValidator<MinimalLengthIfNotEmpty, String> {

    private int min;

    @Override
    public void initialize(MinimalLengthIfNotEmpty constraintAnnotation) {
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String s,
        ConstraintValidatorContext constraintValidatorContext) {
        if (s.isEmpty()) {
            return true;
        } else
            return s.length() >= min;
    }
}
