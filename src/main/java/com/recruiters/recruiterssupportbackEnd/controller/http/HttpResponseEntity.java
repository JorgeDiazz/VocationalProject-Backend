package com.recruiters.recruiterssupportbackEnd.controller.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author JorgeDíaz
 */
public class HttpResponseEntity {

    public static ResponseEntity getNotFoundStatus() {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public static <T> ResponseEntity<T> getOKStatus(T object) {
        return new ResponseEntity<>((T) object, HttpStatus.OK);
    }

    public static ResponseEntity getMissingFieldsStatus() {
        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }

}
