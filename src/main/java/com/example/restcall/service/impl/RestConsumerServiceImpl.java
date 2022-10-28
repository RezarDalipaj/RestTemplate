package com.example.restcall.service.impl;

import com.example.restcall.model.dto.AnimeResponse;
import com.example.restcall.model.dto.SignUpRequest;
import com.example.restcall.model.dto.SignUpResponse;
import com.example.restcall.service.RestConsumerService;
import com.example.restcall.service.RestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RestConsumerServiceImpl implements RestConsumerService {
    private final RestService restService;
    @Override
    public Object getAnime(Integer animeId, Integer episodeId){
        String endpoint = "anime/" + animeId + "/episodes/" + episodeId;
        var response = restService.restCall(endpoint
                , HttpMethod.GET, MediaType.APPLICATION_JSON, null, AnimeResponse.class);
        log.warn(response.getHeaders().toString());
        return response.getBody();
    }
    @Override
    public Object signup(SignUpRequest signUpRequest){
        String endpoint = "http://localhost:8080/signup";
        var response = restService.restCallInternship(endpoint
                , HttpMethod.POST, MediaType.APPLICATION_JSON, signUpRequest, SignUpResponse.class);
        log.warn(response.getHeaders().toString());
        return response.getBody();
    }
}
