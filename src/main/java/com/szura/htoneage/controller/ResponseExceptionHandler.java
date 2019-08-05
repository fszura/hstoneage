package com.szura.htoneage.controller;

import com.szura.htoneage.exception.MessageValidationException;
import com.szura.htoneage.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler({UserNotExistException.class})
    public ResponseEntity<String> handleUserNotExist(UserNotExistException ex, WebRequest request) {
        return ResponseEntity.ok("User do not exist");
    }

    @ExceptionHandler({MessageValidationException.class})
    public ResponseEntity<String> handleMessageValidationException(MessageValidationException ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
