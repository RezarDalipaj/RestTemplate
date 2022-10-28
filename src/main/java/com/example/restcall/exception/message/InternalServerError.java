package com.example.restcall.exception.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternalServerError {
    @JsonProperty
    private int timestamp;
    @JsonProperty
    private int status;
    @JsonProperty
    private String error;
    @JsonProperty
    private String path;
}
