package com.konrad.brk.KFiszki.exception;

public class UserRoleAlreadyExistsException extends RuntimeException {
    public UserRoleAlreadyExistsException(String message) {
        super(message);
    }
}
