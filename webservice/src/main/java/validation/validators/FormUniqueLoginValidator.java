package validation.validators;

import dao.UserDao;
import form.RegisterForm;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class FormUniqueLoginValidator implements ConstraintValidator<UniqueLogin, RegisterForm> {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean isValid(RegisterForm form,
        ConstraintValidatorContext constraintValidatorContext) {
        return userDao.findByLogin(form.getLogin()).orElse(null) == null;
    }
}
