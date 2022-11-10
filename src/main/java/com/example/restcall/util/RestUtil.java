package com.example.restcall.util;

import com.example.restcall.config.RestConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class RestUtil<B, R>{
    ApplicationContext context = new AnnotationConfigApplicationContext(RestConfig.class);
    private final RestTemplate restTemplate = context.getBean(RestTemplate.class);
    private final MyRequest<B, R> request = new MyRequest<>();

    public RestUtil (ReqBuilder<B, R> builder){
        request.setEndpoint(builder.getEndpoint());
        request.setRequestBody(builder.getRequestBody());
        request.setMethod (builder.getMethod());
        request.setResponseClass (builder.getResponseClass());
        request.setMediaType(builder.getMediaType());
        request.setCredentials(builder.getCredentials());
    }

    private HttpHeaders setHeaders(MediaType mediaType, String credentials){
        String key = "0PlesOMDkZz3sDplyXaxWFvzOiQzu7eU";
        String secret = "GNmTQSJyjNtsGEsd";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        if (credentials!=null) {
            if (credentials.contains("Basic"))
                headers.setBasicAuth(key, secret);
            else
                headers.setBearerAuth(credentials);
        }
        return headers;
    }
    public R restCall(){
        HttpEntity<B> requestEntity = new HttpEntity<>(request.getRequestBody(), setHeaders(request.getMediaType(), request.getCredentials()));
        return restTemplate.exchange(request.getEndpoint(), request.getMethod(), requestEntity, request.getResponseClass()).getBody();
    }
}
