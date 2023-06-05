package exception;

import java.sql.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Unexpected error occurred")
public class DaoException extends RuntimeException {

    public DaoException(SQLException e) {
        super(e);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable e) {
        super(e);
    }
}
