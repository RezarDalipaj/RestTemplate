package com.example.restcall.util;

import com.example.restcall.config.RestConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Component
@Setter
@Getter
public class ReqBuilder {
    private String endpoint;
    private HttpMethod method;
    private Object requestBody;
    private MediaType mediaType;
    private String token;
    private String username;
    private String password;
    ApplicationContext context = new AnnotationConfigApplicationContext(RestConfig.class);
    private final RestTemplate restTemplate = context.getBean(RestTemplate.class);
    private final ObjectMapper mapper = context.getBean(ObjectMapper.class);

    public ReqBuilder endpoint(String endpoint) {
        setEndpoint(endpoint);
        return this;
    }

    public ReqBuilder method(HttpMethod method) {
        setMethod(method);
        return this;
    }

    public ReqBuilder requestBody(Object requestBody) {
        setRequestBody(requestBody);
        return this;
    }

    public ReqBuilder mediaType(MediaType mediaType) {
        setMediaType(mediaType);
        return this;
    }

    public ReqBuilder bearerAuthentication(String token) {
        setToken(token);
        return this;
    }
    public ReqBuilder basicAuth(String username, String password){
        setUsername(username);
        setPassword(password);
        return this;
    }
    private HttpHeaders setHeaders(MediaType mediaType, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        if (getUsername()!=null)
            headers.setBasicAuth(username, password);
        else if (token!=null)
            headers.setBearerAuth(token);
        return headers;
    }
    public <R> R execute(Class<R> responseClass){
        HttpEntity<?> requestEntity;
        if (getMediaType() != null && getMediaType().equals(MediaType.APPLICATION_FORM_URLENCODED)){
            MultiValueMap<String, Object> finalBody = new LinkedMultiValueMap<>();
            Map<String, Object> body = mapper.convertValue(getRequestBody(), new TypeReference<>() {});
            finalBody.setAll(body);
            requestEntity = new HttpEntity<>(finalBody, setHeaders(getMediaType(), getToken()));
        }
        else
            requestEntity = new HttpEntity<>(getRequestBody(), setHeaders(getMediaType(), getToken()));
        return restTemplate.exchange(getEndpoint(), getMethod(), requestEntity, responseClass).getBody();
    }
}
