package handlers;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionJsonResponse> handleException() {
        return new ResponseEntity<>(new ExceptionJsonResponse("Server error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionJsonResponse> handleJwtException(JwtException e) {
        return new ResponseEntity<>(new ExceptionJsonResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }


}
