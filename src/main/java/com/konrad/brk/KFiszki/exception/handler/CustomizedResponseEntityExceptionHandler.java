package com.konrad.brk.KFiszki.exception.handler;

import com.konrad.brk.KFiszki.exception.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> fieldErrorMessages = extractFieldErrorMessages(ex);
        ExceptionResponse response = new ExceptionResponse(new Date(), fieldErrorMessages, request.getDescription(false));
        return new ResponseEntity(response, BAD_REQUEST);
    }

    private List<String> extractFieldErrorMessages(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return getDefaultFieldsErrorMessages(fieldErrors);
    }

    private List<String> getDefaultFieldsErrorMessages(List<FieldError> fieldErrors) {
        List<String> errorMessages = new ArrayList<>();
        for(FieldError fieldError: fieldErrors){
            errorMessages.add(fieldError.getDefaultMessage());
        }
        return errorMessages;
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), Collections.singletonList(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), Collections.singletonList(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity(exResponse, BAD_REQUEST);
    }

    @ExceptionHandler(UserCredentialsNotValidException.class)
    public ResponseEntity handleUserCredentialsNotValidException(UserCredentialsNotValidException ex, WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), Collections.singletonList(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UserRoleAlreadyExistsException.class)
    public ResponseEntity handleUserRoleAlreadyExistsException(UserRoleAlreadyExistsException ex, WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), Collections.singletonList(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity(exResponse, BAD_REQUEST);
    }

    @ExceptionHandler(FlashcardPackageAlreadyExistsException.class)
    public ResponseEntity handleFlashcardPackageAlreadyExistsException(FlashcardPackageAlreadyExistsException ex, WebRequest request){
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), Collections.singletonList(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlashcardPackageNotFoundException.class)
    public ResponseEntity handleFlashcardPackageNotFoundException(FlashcardPackageNotFoundException ex, WebRequest request){
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), Collections.singletonList(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlashcardAlreadyExistsException.class)
    public ResponseEntity handleFlashcardAlreadyExistsException(FlashcardAlreadyExistsException ex, WebRequest request){
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), Collections.singletonList(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRoleNotFoundException.class)
    public ResponseEntity handleUserRoleNotFoundException(UserRoleNotFoundException ex, WebRequest request){
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), Collections.singletonList(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
    }
}
