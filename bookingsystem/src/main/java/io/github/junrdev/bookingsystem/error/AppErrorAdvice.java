package io.github.junrdev.bookingsystem.error;

import io.github.junrdev.bookingsystem.error.model.ClientNotFoundException;
import io.github.junrdev.bookingsystem.error.model.CompanyNotFoundException;
import io.github.junrdev.bookingsystem.error.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class AppErrorAdvice {

    public static Logger LOGGER = LoggerFactory.getLogger("ErrorAdvice");

    //failed to resolve path /path -> NOT_FOUNG
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException exception) {
        return new ResponseEntity<>(
                ErrorResponse
                        .builder()
                        .statusCode(404)
                        .message("Failed to match the requested Url. Path [" + exception.getResourcePath() + "]")
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    //violation of primary keys
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        LOGGER.info(exception.getLocalizedMessage());
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message("You have duplicate fields.")
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(exception.getMessage())
                        .statusCode(exception.getStatusCode().value())
                        .build(),
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    @ExceptionHandler({CompanyNotFoundException.class, ClientNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleCompanyNotFoundException(RuntimeException exception) {
            return new ResponseEntity<>(ErrorResponse.builder().message(exception.getMessage()).statusCode(HttpStatus.NOT_FOUND.value()).build(), HttpStatus.NOT_FOUND);
    }


}
