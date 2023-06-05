package validation.validators;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueEmailValidator.class, FormUniqueEmailValidator.class })
@Repeatable(UniqueEmailRepeatableContainer.class)
public @interface UniqueEmail {
    String errorCode() default "email";
    boolean editMode() default false;
    String message() default "User with this email already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
