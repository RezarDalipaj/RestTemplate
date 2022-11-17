package com.example.restcall.service;

import com.example.restcall.model.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ConsumerService {
//    ResponseEntity<?> getAnime(Integer animeId, Integer episodeId) throws IOException;

    ResponseEntity<?> getAnime(Integer animeId, Integer episodeId) throws IOException;

    ResponseEntity<?> getToken();

    ResponseEntity<?> signup(SignUpRequest signUpRequest) throws Exception;

    ResponseEntity<?> getUsers() throws Exception;
}
