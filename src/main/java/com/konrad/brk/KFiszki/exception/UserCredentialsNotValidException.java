package com.konrad.brk.KFiszki.exception;

public class UserCredentialsNotValidException extends RuntimeException{
    public UserCredentialsNotValidException(String message) {
        super(message);
    }
}
