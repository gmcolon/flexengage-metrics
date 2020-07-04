package com.flexengage.metrics.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionInterceptor {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object>  requestHandlingNoHandlerFound() {
        return new ResponseEntity("Action not found. Please check request and try again", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public final ResponseEntity<Object> handleAllExceptions(BadRequestException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
