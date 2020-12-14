package com.konrad.brk.KFiszki.exception.handler;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExceptionResponse {
    private Date date;
    private List<String> messages = new ArrayList<>();
    private String details;


    public ExceptionResponse(Date date, List<String> messages, String details) {
        this.date = date;
        this.messages = messages;
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getDetails() {
        return details;
    }


    public void addFieldErrors(List<FieldError> fieldErrors){
        for(FieldError fieldError: fieldErrors){
            messages.add(fieldError.getDefaultMessage());
        }
    }
}
