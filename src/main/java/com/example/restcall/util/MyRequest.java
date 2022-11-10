package com.example.restcall.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyRequest <B, R> {
    private String endpoint;
    private HttpMethod method;
    private B requestBody;
    private Class<R> responseClass;
    private MediaType mediaType;
    private String credentials;
}
