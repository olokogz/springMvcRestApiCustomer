package main.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerNotFoundException(NotFoundException exc)
    {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                                   exc.getMessage(),
                                                   LocalDateTime.now());

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerGeneralException(Exception exc)
    {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
