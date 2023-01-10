package com.example.restcall.model.dto.hungerNet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Double balance;
    private List<String> roles;
}
