package com.recruiters.recruiterssupportbackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;

@SpringBootApplication
@EnableAutoConfiguration

public class RecruitersSupportBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitersSupportBackEndApplication.class, args);
    }
}
