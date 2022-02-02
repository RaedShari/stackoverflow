package com.raed.stackoverflow.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public abstract class BaseException extends RuntimeException {

    private String sourceError;
    private String fieldError;
    private String messageKey;
    private String locale;


    public BaseException(){
        super();
    }

    public BaseException(String messageKey, String sourceError, String fieldError) {
        super(messageKey);

        this.sourceError = sourceError;
        this.fieldError = fieldError;
        this.messageKey = messageKey;
    }

    public abstract HttpStatus getStatusCode();
}
