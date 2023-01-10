package com.example.restcall.controller;

import com.example.restcall.model.dto.hungerNet.SignUpRequest;
import com.example.restcall.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@org.springframework.web.bind.annotation.RestController
@RequestMapping()
@RequiredArgsConstructor
public class RestController {
    private final ConsumerService restConsumerService;
    @GetMapping("/anime/{animeId}/episode/{episodeId}")
    public ResponseEntity<?> getAnime(@PathVariable(name = "animeId") Integer animeId
            , @PathVariable("episodeId") Integer episodeId) throws IOException {
        return restConsumerService.getAnime(animeId, episodeId);
    }
    @GetMapping("/token")
    public ResponseEntity<?> getToken() {
        return restConsumerService.getToken();
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) throws Exception {
        return restConsumerService.signup(signUpRequest);
    }
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return restConsumerService.getUsers();
    }
}
