package com.example.restcall.exception.handler;

import com.example.restcall.exception.message.ErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.io.IOException;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionHandler {
    private final ObjectMapper objectMapper;
//    public ResponseEntity<?> handleError(ClientHttpResponse response) throws IOException {
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setStatus(response.getStatusText());
//        errorDto.setMessage(response.getBody().toString());
//    }
//    private ResponseEntity<?> containsString(Exception exception, List<String> messagesToBeCompared, String status, String message){
//        ErrorDto errorDto = new ErrorDto();
//        boolean hasBroken = false;
//        for (String messageToBeCompared: messagesToBeCompared) {
//            if (exception.getMessage().toLowerCase().contains(messageToBeCompared)) {
//                hasBroken = true;
//                break;
//            }
//        }
//        if (hasBroken){
//            errorDto.setStatus(status);
//            errorDto.setMessage(message);
//            return ResponseEntity.status(Integer.parseInt(status)).body(errorDto);
//        }
//        return null;
//    }
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleClientErrorException(
            HttpClientErrorException clientErrorException) throws IOException {
        ErrorDto errorDto = new ErrorDto();
//        errorDto.setStatus(clientErrorException.getStatusCode());
        byte [] message = clientErrorException.getResponseBodyAsByteArray();
        errorDto.setStackTrace(objectMapper.readValue(message, Object.class));
        return ResponseEntity.status(clientErrorException.getStatusCode()).body(errorDto);
//        String realMessage = message.replace("{", "")
//                .replace("}", "")
//                .replace("/", "")
//                .replace("\"","")
//                .replace(",",", ");
//        int index = realMessage.indexOf("message");
//        String finalMessage = realMessage.substring(index + "message:".length());
//        errorDto.setMessage(finalMessage);
//        List<String> unauthorized = new ArrayList<>(List.of("401", "unauthorized"));
//        var response401 = containsString(clientErrorException
//                ,unauthorized,"401","Unauthorized");
//        if (response401 != null)
//            return response401;
//        List<String> notFound = new ArrayList<>(List.of("404", "not found"));
//        var response404 = containsString(clientErrorException
//                ,notFound,"404","Resource not found");
//        if (response404 != null)
//            return response404;
//        var errorDtoFromApi = mapper.readValue(clientErrorException
//                .getMessage().substring(7), ErrorDto.class);
//        return ResponseEntity.status(400).body(errorDtoFromApi);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<?> handleServerErrorException(
            HttpServerErrorException serverErrorException) throws IOException {
        ErrorDto errorDto = new ErrorDto();
        byte [] message = serverErrorException.getResponseBodyAsByteArray();
        errorDto.setStackTrace(objectMapper.readValue(message, Object.class));
        return ResponseEntity.status(serverErrorException.getStatusCode()).body(errorDto);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(UnknownHttpStatusCodeException.class)
    public ResponseEntity<?> handleUnknownStatusCodeException(
            UnknownHttpStatusCodeException statusCodeException) throws IOException {
        ErrorDto errorDto = new ErrorDto();
        byte [] message = statusCodeException.getResponseBodyAsByteArray();
        errorDto.setStackTrace(objectMapper.readValue(message, Object.class));
        return ResponseEntity.status(statusCodeException.getRawStatusCode()).body(errorDto);
    }
}
