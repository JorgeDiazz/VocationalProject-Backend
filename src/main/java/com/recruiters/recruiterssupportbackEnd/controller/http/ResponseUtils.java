package com.recruiters.recruiterssupportbackEnd.controller.http;

import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import org.springframework.http.HttpHeaders;

public class ResponseUtils {

    public static HttpHeaders generateTokenHeader(UserEntity userEntity) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");
        return headers;
    }

}
