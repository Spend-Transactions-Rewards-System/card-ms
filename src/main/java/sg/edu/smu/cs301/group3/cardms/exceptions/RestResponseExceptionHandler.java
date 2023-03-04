package sg.edu.smu.cs301.group3.cardms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiError> handleException(Exception e) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ApiError> nullPointerException(Exception e) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage(),e);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
