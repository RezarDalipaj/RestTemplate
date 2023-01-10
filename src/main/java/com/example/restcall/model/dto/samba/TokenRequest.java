package com.example.restcall.model.dto.samba;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRequest {
    private String grant_type;
    private String customer_number;
    private String scope;
}
