package com.konrad.brk.KFiszki.exception.handler;

import com.konrad.brk.KFiszki.exception.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserCredentialsNotValidException.class)
    public ResponseEntity handleUserCredentialsNotValidException(UserCredentialsNotValidException ex, WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UserRoleAlreadyExistsException.class)
    public ResponseEntity handleUserRoleAlreadyExistsException(UserRoleAlreadyExistsException ex, WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlashcardPackageAlreadyExistsException.class)
    public ResponseEntity handleFlashcardPackageAlreadyExistsException(FlashcardPackageAlreadyExistsException ex, WebRequest request){
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlashcardPackageNotFoundException.class)
    public ResponseEntity handleFlashcardPackageNotFoundException(FlashcardPackageNotFoundException ex, WebRequest request){
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlashcardAlreadyExistsException.class)
    public ResponseEntity handleFlashcardAlreadyExistsException(FlashcardAlreadyExistsException ex, WebRequest request){
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRoleNotFoundException.class)
    public ResponseEntity handleUserRoleNotFoundException(UserRoleNotFoundException ex, WebRequest request){
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
    }
}
