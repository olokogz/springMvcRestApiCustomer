package main.controller.errors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @FunctionalInterface
    interface ErrorList{
        public String getErrors(StackTraceElement[] stack);
    }

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
        exc.printStackTrace();
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
               exc.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
