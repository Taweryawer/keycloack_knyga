package validation.validators;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = MinimalLengthIfNotEmptyValidator.class)
public @interface MinimalLengthIfNotEmpty {
    int min() default 0;
    String message() default "Field can't be empty";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
