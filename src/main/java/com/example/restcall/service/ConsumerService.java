package com.example.restcall.service;

import com.example.restcall.model.dto.hungerNet.SignUpRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ConsumerService {
    ResponseEntity<?> getAnime(Integer animeId, Integer episodeId) throws IOException;

    ResponseEntity<?> getToken();

    ResponseEntity<?> signup(SignUpRequest signUpRequest) throws IOException;

    ResponseEntity<?> getUsers();
}
