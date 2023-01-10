package com.example.restcall.service.impl;

import com.example.restcall.config.BaseProperties;
import com.example.restcall.model.dto.*;
import com.example.restcall.service.ConsumerService;
import com.example.restcall.util.ReqBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {
    private final ObjectMapper objectMapper;
    private final BaseProperties baseProperties;
    @Override
    public ResponseEntity<?> getAnime(Integer animeId, Integer episodeId) throws IOException {
        String endpoint = baseProperties.getUrl() + "anime/" + animeId + "/episodes/" + episodeId;
        try {
            var response = ReqBuilder.createRequest()
                    .endpoint(endpoint)
                    .method(HttpMethod.GET)
                    .execute(AnimeResponse.class);
            return ResponseEntity.ok(response);
        }catch (HttpClientErrorException exception){
            byte [] message = exception.getResponseBodyAsByteArray();
            AnimeError error = objectMapper.readValue(message, AnimeError.class);
            return ResponseEntity.status(exception.getStatusCode()).body(error);
        }
    }
    private String getCustomerNo() {
        String endpoint = "user/v3/custno?scn=992007407580526";
        var response = ReqBuilder.createRequest()
                .endpoint(endpoint)
                .method(HttpMethod.GET)
                .basicAuth(baseProperties.getKey(), baseProperties.getSecret())
                .execute(CustomerNumberResponse.class);
        return response.getCustNo();
    }
    @Override
    public ResponseEntity<?> getToken() {
        String endpoint = "oauth2/token";
        var tokenRequest = TokenRequest.builder()
            .customer_number(getCustomerNo())
            .grant_type("client_credentials")
            .scope("ANONYMOUS IDENTIFIED AUTHENTICATED")
            .build();
        var response = ReqBuilder.createRequest()
                .endpoint(endpoint)
                .method(HttpMethod.POST)
                .requestBody(tokenRequest)
                .mediaType(MediaType.APPLICATION_FORM_URLENCODED)
                .basicAuth(baseProperties.getKey(), baseProperties.getSecret())
                .execute(TokenResponse.class);
        return ResponseEntity.ok(response);
    }
    @Override
    public ResponseEntity<?> signup(SignUpRequest signUpRequest) throws Exception {
        String endpoint = baseProperties.getInternship() + "signup";
        try {
            var response = ReqBuilder.createRequest()
                    .endpoint(endpoint)
                    .method(HttpMethod.POST)
                    .mediaType(MediaType.APPLICATION_JSON)
                    .requestBody(signUpRequest)
                    .execute(SignUpResponse.class);
            return ResponseEntity.ok(response);
        }catch (HttpClientErrorException httpException){
            byte [] message = httpException.getResponseBodyAsByteArray();
            var error = objectMapper.readValue(message, HungerNetError.class);
            return ResponseEntity.status(httpException.getStatusCode()).body(error);
        }
    }
    private String getInternshipToken() {
        String endpoint = baseProperties.getInternship() + "login";
        var loginRequest = new LoginRequest("rezari", "lhind");
        var response = ReqBuilder.createRequest()
                .endpoint(endpoint)
                .method(HttpMethod.POST)
                .mediaType(MediaType.APPLICATION_JSON)
                .requestBody(loginRequest)
                .execute(LoginResponse.class);
        return response.getToken();
    }
    @Override
    public ResponseEntity<?> getUsers() {
        String endpoint = baseProperties.getInternship() + "users";
        var response = ReqBuilder.createRequest()
                .endpoint(endpoint)
                .method(HttpMethod.GET)
                .bearerAuthentication(getInternshipToken())
                .execute(List.class);
        List<SignUpResponse> users = objectMapper.convertValue(response, new TypeReference<>() {});
        return ResponseEntity.ok(users);
    }
}
