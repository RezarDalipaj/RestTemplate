package com.example.restcall.service.impl;

import com.example.restcall.service.RestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@AllArgsConstructor
public class RestServiceImpl implements RestService {
    private final RestTemplate restTemplate;
    @Qualifier("internship")
    private final RestTemplate restTemplateInternship;

    private HttpHeaders setHeaders(MediaType mediaType){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        return headers;
    }

    @Override
    public ResponseEntity<?> restCall(String url, HttpMethod method
            , MediaType mediaType, Object request, Class<?> responseClass){
        HttpEntity<Object> requestEntity = new HttpEntity<>(request, setHeaders(mediaType));
        return restTemplate.exchange(url, method, requestEntity, responseClass);
    }
    @Override
    public ResponseEntity<?> restCallInternship(String url, HttpMethod method
            , MediaType mediaType, Object request, Class<?> responseClass){
        HttpEntity<Object> requestEntity = new HttpEntity<>(request, setHeaders(mediaType));
        return restTemplateInternship.exchange(url, method, requestEntity, responseClass);
    }
}
