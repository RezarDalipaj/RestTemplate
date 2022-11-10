package com.example.restcall.service;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ConsumerService {
    ResponseEntity<?> getAnime(Integer animeId, Integer episodeId) throws IOException;

}
