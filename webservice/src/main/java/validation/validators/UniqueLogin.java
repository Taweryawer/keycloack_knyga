package validation.validators;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueLoginValidator.class, FormUniqueLoginValidator.class })
public @interface UniqueLogin {
    String errorCode() default "login";
    String message() default "User with this login already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
