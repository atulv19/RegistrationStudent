package com.apidemo.exception;

import com.apidemo.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandle {

    // Handle ResourceNotFoudException
    @ExceptionHandler(ResourceNotFoudException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(
            ResourceNotFoudException ex,
            WebRequest request
    ){
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(),
                new Date(),
                request.getDescription(false) // false => no sensitive info
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllExceptions(
            Exception ex,
            WebRequest request
    ) {
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(),
                new Date(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
