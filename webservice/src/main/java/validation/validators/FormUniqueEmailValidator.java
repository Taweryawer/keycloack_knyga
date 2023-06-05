package validation.validators;

import dao.UserDao;
import domain.User;
import form.RegisterForm;
import java.util.Optional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class FormUniqueEmailValidator implements
    ConstraintValidator<UniqueEmail, RegisterForm> {

    @Autowired
    private UserDao userDao;
    private boolean editMode;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        this.editMode = constraintAnnotation.editMode();
    }

    @Override
    public boolean isValid(RegisterForm form,
        ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = userDao.findByLogin(form.getLogin());
        if (editMode && user.isPresent()) {
            if (user.get().getEmail().equals(form.getEmail()))
                return true;
        }
        return userDao.findByEmail(form.getEmail()).orElse(null) == null;
    }
}
