package com.example.restcall.controller;

import com.example.restcall.model.dto.SignUpRequest;
import com.example.restcall.service.RestConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@AllArgsConstructor
public class AnimeController {
    private final RestConsumerService restConsumerService;
    @GetMapping("/anime/{animeId}/episode/{episodeId}")
    public ResponseEntity<?> getAnime(@PathVariable(name = "animeId") Integer animeId
            , @PathVariable("episodeId") Integer episodeId) {
        return ResponseEntity.ok(restConsumerService.getAnime(animeId, episodeId));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(restConsumerService.signup(signUpRequest));
    }
}
