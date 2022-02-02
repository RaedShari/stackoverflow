package com.raed.stackoverflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException  extends BaseException{

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NO_CONTENT;
    }
}