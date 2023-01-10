package com.example.restcall.model.dto.samba;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerNumberResponse {
    private String custNo;
    private String ucid;
}