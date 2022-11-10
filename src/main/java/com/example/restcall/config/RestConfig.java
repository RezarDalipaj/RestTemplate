package com.example.restcall.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;


@Configuration
@Slf4j
public class RestConfig {
    private final BaseProperties baseProperties;

    public RestConfig(BaseProperties baseProperties) {
        this.baseProperties = baseProperties;
    }

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
        return new RestTemplate();
    }
    @Bean
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }
}
