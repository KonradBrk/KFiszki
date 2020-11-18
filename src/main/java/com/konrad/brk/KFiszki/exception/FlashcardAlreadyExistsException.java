package com.konrad.brk.KFiszki.exception;

public class FlashcardAlreadyExistsException extends RuntimeException{
    public FlashcardAlreadyExistsException(String message) {
        super(message);
    }
}
