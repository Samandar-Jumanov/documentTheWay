package com.dtw.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            WebRequest request , ResourceNotFoundException exception
    ){

        ErrorDetails errDetails = new ErrorDetails(
                request.getDescription(false),
                "NOT_FOUND",
                LocalTime.now(),
                exception.getMessage()
        );

        return  new ResponseEntity<ErrorDetails>(errDetails , HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UsernameTakenException.class)
    public ResponseEntity<ErrorDetails> handleUsernameTakenException(
            WebRequest request , UsernameTakenException exception
    ){

        ErrorDetails errDetails = new ErrorDetails(
                request.getDescription(false),
                "BAD_REQUEST",
                LocalTime.now(),
                "Username already taken "
        );


        return  new ResponseEntity<ErrorDetails>(errDetails , HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(
            WebRequest request , Exception exception
    ){

        ErrorDetails errDetails = new ErrorDetails(
                request.getDescription(false),
                "INTERNAL_SERVER_ERROR",
                LocalTime.now(),
                "INTERNAL_SERVER_ERROR"
        );


        System.out.println(exception.getMessage());

        return  new ResponseEntity<ErrorDetails>(errDetails , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        super.handleMethodArgumentNotValid(ex, headers, status, request);
        Map<String , String > errors = new HashMap<>();
        List<ObjectError> errList = ex.getBindingResult().getAllErrors();

        errList.forEach( err ->{
            String fieldName = ((FieldError ) err).getField();
            String errMessage = err.getDefaultMessage();
            errors.put(fieldName , errMessage);
        });



        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
    }
}
