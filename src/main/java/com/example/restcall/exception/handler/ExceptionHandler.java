package com.example.restcall.exception.handler;

import com.example.restcall.exception.message.ErrorDto;
import com.example.restcall.exception.message.InternalServerError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionHandler {
    private final ObjectMapper mapper;
    private ResponseEntity<?> containsString(Exception exception, List<String> messagesToBeCompared, String status, String message){
        ErrorDto errorDto = new ErrorDto();
        boolean hasBroken = false;
        for (String messageToBeCompared: messagesToBeCompared) {
            if (exception.getMessage().toLowerCase().contains(messageToBeCompared)) {
                hasBroken = true;
                break;
            }
        }
        if (hasBroken){
            errorDto.setStatus(status);
            errorDto.setMessage(message);
            return ResponseEntity.status(Integer.parseInt(status)).body(errorDto);
        }
        return null;
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleClientErrorException(
            HttpClientErrorException clientErrorException) throws JsonProcessingException {
        List<String> unauthorized = new ArrayList<>(List.of("401", "unauthorized"));
        var response401 = containsString(clientErrorException
                ,unauthorized,"401","Unauthorized");
        if (response401 != null)
            return response401;
        List<String> notFound = new ArrayList<>(List.of("404", "not found"));
        var response404 = containsString(clientErrorException
                ,notFound,"404","Resource not found");
        if (response404 != null)
            return response404;
        var errorDtoFromApi = mapper.readValue(clientErrorException
                .getMessage().substring(7), ErrorDto.class);
        return ResponseEntity.status(400).body(errorDtoFromApi);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<?> handleServerErrorException(
            HttpServerErrorException serverErrorException) throws JsonProcessingException {
        var errorFromApi = mapper.readValue(serverErrorException
                .getMessage().substring(7), InternalServerError.class);
        return ResponseEntity.status(500).body(errorFromApi);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(UnknownHttpStatusCodeException.class)
    public ResponseEntity<?> handleUnknownStatusCodeException(
            UnknownHttpStatusCodeException unknownHttpStatusCodeException){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("400");
        errorDto.setMessage(unknownHttpStatusCodeException.getMessage());
        return ResponseEntity.status(400).body(errorDto);
    }
}
