package com.recruiters.recruiterssupportbackEnd.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author JorgeDíaz
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends Exception {

    public ConflictException(String message) {
        super(message);
    }

}