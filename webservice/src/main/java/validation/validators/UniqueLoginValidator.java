package validation.validators;

import dao.UserDao;
import dto.UserDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, UserDto> {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean isValid(UserDto userDto,
        ConstraintValidatorContext constraintValidatorContext) {
        return userDao.findByLogin(userDto.getLogin()).orElse(null) == null;
    }
}
