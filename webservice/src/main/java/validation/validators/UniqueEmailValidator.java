package validation.validators;

import dao.UserDao;
import domain.User;
import dto.UserDto;
import java.util.Optional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, UserDto> {

    @Autowired
    private UserDao userDao;
    private boolean editMode;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        this.editMode = constraintAnnotation.editMode();
    }

    @Override
    public boolean isValid(UserDto userDto,
        ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = userDao.findByLogin(userDto.getLogin());
        if (editMode && user.isPresent()) {
            if (user.get().getEmail().equals(userDto.getEmail()))
                return true;
        }
        return userDao.findByEmail(userDto.getEmail()).orElse(null) == null;
    }
}
