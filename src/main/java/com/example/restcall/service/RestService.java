package com.example.restcall.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface RestService {
    ResponseEntity<?> restCall(String url, HttpMethod method, MediaType mediaType, Object request, Class<?> classObject);

    ResponseEntity<?> restCallInternship(String url, HttpMethod method, MediaType mediaType, Object request, Class<?> responseClass);
}
