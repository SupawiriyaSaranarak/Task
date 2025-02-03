package com.example.task.config;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.task.interceptor.LoggingInterceptor;

@Configuration
public class RestTemplateConfiguration {
	
	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
        		.interceptors(List.of(new LoggingInterceptor()))
        		.build();
    }

}
