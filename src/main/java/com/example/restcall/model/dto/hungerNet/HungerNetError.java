package com.example.restcall.model.dto.hungerNet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HungerNetError {
    private String status;
    private String message;
}

