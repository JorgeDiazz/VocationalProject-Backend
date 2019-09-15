package com.recruiters.recruiterssupportbackEnd.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ExpectationFailedException extends Exception {

    public ExpectationFailedException(String message) {
        super(message);
    }
}
