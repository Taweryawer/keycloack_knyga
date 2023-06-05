package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unexpected error occurred")
public class NoDefaultRoleException extends RuntimeException {

    public NoDefaultRoleException(String message) {
        super(message);
    }
}
