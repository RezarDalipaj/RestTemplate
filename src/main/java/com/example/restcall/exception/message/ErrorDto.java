package com.example.restcall.exception.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
//    @JsonProperty
//    private HttpStatus status;
    @JsonProperty
    private Object stackTrace;
}
