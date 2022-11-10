package com.example.restcall.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class ReqBuilder<B, R> {
    private String endpoint;
    private HttpMethod method;
    private B requestBody;
    private Class<R> responseClass;
    private MediaType mediaType;
    private String credentials;

    public ReqBuilder<B, R> endpoint(String endpoint) {
        setEndpoint(endpoint);
        return this;
    }

    public ReqBuilder<B, R> method(HttpMethod method) {
        setMethod(method);
        return this;
    }

    public ReqBuilder<B, R> requestBody(B requestBody) {
        setRequestBody(requestBody);
        return this;
    }

    public ReqBuilder<B, R> responseClass(Class<R> responseClass) {
        setResponseClass(responseClass);
        return this;
    }

    public ReqBuilder<B, R> mediaType(MediaType mediaType) {
        setMediaType(mediaType);
        return this;
    }

    public ReqBuilder<B, R> credentials(String credentials) {
        setCredentials(credentials);
        return this;
    }

    public RestUtil<B, R> build(){
        return new RestUtil<>(this);
    }

}
