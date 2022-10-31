package com.example.restcall.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;


@Configuration
@AllArgsConstructor
@Slf4j
public class RestConfig {
    private final BaseProperties baseProperties;
    @Primary
    @Bean
    public RestTemplate getRestTemplate(){
        var uriBuilderFactory = new DefaultUriBuilderFactory(baseProperties.getUrl());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(uriBuilderFactory);
        return restTemplate;
    }
    @Qualifier("internship")
    @Bean()
    public RestTemplate getRestTemplateInternship(){
//        var uriBuilderFactory = new DefaultUriBuilderFactory(baseProperties.getInternship());
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setUriTemplateHandler(uriBuilderFactory);
//        return restTemplate;
        return new RestTemplate();
    }
    @Bean
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }
}
