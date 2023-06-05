package validation.validators;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { PasswordMatchesValidator.class, FormPasswordMatchesValidator.class })
public @interface PasswordMatches {
    String errorCode() default "passwordConfirmation";
    String message() default "Passwords don't match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
