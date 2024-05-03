package com.justice.nkopane.justnotes.exception;


import com.justice.nkopane.justnotes.user.UserExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        ApiErroResponse apiErroResponse = new ApiErroResponse(ZonedDateTime.now(), HttpStatus.BAD_REQUEST, "Validation Error", request.getServletPath());
        return new ResponseEntity<>(apiErroResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> handleUserExistsException(UserExistsException e, HttpServletRequest request){
        ApiErroResponse apiErroResponse = new ApiErroResponse(ZonedDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiErroResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
