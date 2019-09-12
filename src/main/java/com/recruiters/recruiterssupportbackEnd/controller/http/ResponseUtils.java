package com.recruiters.recruiterssupportbackEnd.controller.http;

import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import org.springframework.http.HttpHeaders;

public class ResponseUtils {

    public static HttpHeaders generateTokenHeader(UserEntity userEntity) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");
        headers.add("Access-Control-Allow-Methods", "POST,GET");
        headers.add("Access-Control-Allow-Headers", "Content-Type,Authorization");
        headers.add("Access-Control-Expose-Headers", "token");
        return headers;

    }

}
