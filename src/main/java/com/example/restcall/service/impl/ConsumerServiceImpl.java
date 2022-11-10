package com.example.restcall.service.impl;

import com.example.restcall.model.dto.AnimeError;
import com.example.restcall.model.dto.AnimeResponse;
import com.example.restcall.service.ConsumerService;
import com.example.restcall.util.ReqBuilder;
import com.example.restcall.util.RestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

@Service
@AllArgsConstructor
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {
    private final ObjectMapper objectMapper;
    @Override
    public ResponseEntity<?> getAnime(Integer animeId, Integer episodeId) throws IOException {
        String endpoint = "anime/" + animeId + "/episodes/" + episodeId;
        try {
            RestUtil<Object, AnimeResponse> restServiceAnime = new ReqBuilder<Object, AnimeResponse>()
                    .endpoint(endpoint)
                    .method(HttpMethod.GET)
                    .responseClass(AnimeResponse.class)
                    .build();
            var response = restServiceAnime.restCall();
            return ResponseEntity.ok(response);
        }catch (HttpClientErrorException exception){
            byte [] message = exception.getResponseBodyAsByteArray();
            AnimeError error = objectMapper.readValue(message, AnimeError.class);
            return ResponseEntity.status(exception.getStatusCode()).body(error);
        }
    }
}
