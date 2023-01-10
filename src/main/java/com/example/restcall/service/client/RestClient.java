package com.example.restcall.service.client;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Setter
@Getter
public class RestClient {
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

    public static RestClient createRequest(){
        return new RestClient();
    }

    public RestClient endpoint(String endpoint) {
        setEndpoint(endpoint);
        return this;
    }

    public RestClient method(HttpMethod method) {
        setMethod(method);
        return this;
    }

    public RestClient requestBody(Object requestBody) {
        setRequestBody(requestBody);
        return this;
    }

    public RestClient mediaType(MediaType mediaType) {
        setMediaType(mediaType);
        return this;
    }

    public RestClient bearerAuthentication(String token) {
        setToken(token);
        return this;
    }
    public RestClient basicAuth(String username, String password){
        setUsername(username);
        setPassword(password);
        return this;
    }
    private HttpHeaders setHeaders(MediaType mediaType){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        if (getUsername()!=null)
            headers.setBasicAuth(username, password);
        else if (getToken()!=null)
            headers.setBearerAuth(token);
        return headers;
    }
    public <R> R execute(Class<R> responseClass){
        HttpEntity<?> requestEntity;
        if (getMediaType() != null && MediaType.APPLICATION_FORM_URLENCODED.equals(getMediaType())){
            MultiValueMap<String, Object> finalBody = new LinkedMultiValueMap<>();
            Map<String, Object> body = mapper.convertValue(getRequestBody(), new TypeReference<>() {});
            finalBody.setAll(body);
            requestEntity = new HttpEntity<>(finalBody, setHeaders(getMediaType()));
        }
        else
            requestEntity = new HttpEntity<>(getRequestBody(), setHeaders(getMediaType()));
        return restTemplate.exchange(getEndpoint(), getMethod(), requestEntity, responseClass).getBody();
    }
}
