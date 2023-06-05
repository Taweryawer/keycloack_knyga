package util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationUtils {

    public static Map<String, String> getErrorsMapForUserForm(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField() , error.getDefaultMessage());
        }
        for (ObjectError error : result.getGlobalErrors()) {
            errors.put(error.getCode(), error.getDefaultMessage());
        }
        return errors;
    }
}
