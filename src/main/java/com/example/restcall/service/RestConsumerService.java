package com.example.restcall.service;

import com.example.restcall.model.dto.SignUpRequest;

public interface RestConsumerService {
    Object getAnime(Integer animeId, Integer episodeId);

    Object signup(SignUpRequest signUpRequest);
}
