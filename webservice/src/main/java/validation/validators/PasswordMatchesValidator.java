package validation.validators;

import dto.UserDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDto> {

    @Override
    public boolean isValid(UserDto userDto,
        ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
