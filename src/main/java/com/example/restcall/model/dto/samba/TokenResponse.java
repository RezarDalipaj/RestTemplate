package com.example.restcall.model.dto.samba;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private String access_token;
    private Integer expires_in;
    private String scope;
    private String token_type;
}