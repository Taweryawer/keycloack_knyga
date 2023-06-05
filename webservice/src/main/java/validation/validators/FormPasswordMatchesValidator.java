package validation.validators;

import form.RegisterForm;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FormPasswordMatchesValidator implements
    ConstraintValidator<PasswordMatches, RegisterForm> {

    @Override
    public boolean isValid(RegisterForm form,
        ConstraintValidatorContext constraintValidatorContext) {
        return form.getPassword().equals(form.getPasswordr());
    }
}
