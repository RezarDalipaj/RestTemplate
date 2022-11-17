package com.example.restcall.exception.handler;

import com.example.restcall.exception.message.ErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.io.IOException;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionHandler {
    private final ObjectMapper objectMapper;
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorDto> handleServerErrorException(
            HttpServerErrorException serverErrorException) throws IOException {
        ErrorDto errorDto = new ErrorDto();
        byte [] message = serverErrorException.getResponseBodyAsByteArray();
        errorDto.setError(objectMapper.readValue(message, Object.class));
        return ResponseEntity.status(serverErrorException.getStatusCode()).body(errorDto);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(UnknownHttpStatusCodeException.class)
    public ResponseEntity<ErrorDto> handleUnknownStatusCodeException(
            UnknownHttpStatusCodeException statusCodeException) throws IOException {
        ErrorDto errorDto = new ErrorDto();
        byte [] message = statusCodeException.getResponseBodyAsByteArray();
        errorDto.setError(objectMapper.readValue(message, Object.class));
        return ResponseEntity.status(statusCodeException.getRawStatusCode()).body(errorDto);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError(exception.getMessage());
        return ResponseEntity.status(500).body(errorDto);
    }
}
