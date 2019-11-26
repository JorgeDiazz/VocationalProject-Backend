/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author jhanuar sanchez
 */
@Configuration
public class WebCorsConfigurer {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")// aceptar desde cualquier ip
                        .allowedOrigins("http://localhost:4200")// aceptar desde cualquier destino url
                        .allowedMethods("PUT", "DELETE", "POST", "GET")
                        .allowedHeaders("Content-Type", "Authorization")
                        .exposedHeaders("Content-Type", "Authorization");
            }
        };
    }
}
