package com.example.restcall.controller;

import com.example.restcall.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping()
@AllArgsConstructor
public class AnimeController {
    private final ConsumerService restConsumerService;
    @GetMapping("/anime/{animeId}/episode/{episodeId}")
    public ResponseEntity<?> getAnime(@PathVariable(name = "animeId") Integer animeId
            , @PathVariable("episodeId") Integer episodeId) throws IOException {
        return restConsumerService.getAnime(animeId, episodeId);
    }
}
