package com.example.restcall.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DataDto {
    private int mal_id;
    private String url;
    private String title;
    private String title_japanese;
    private String title_romanji;
    private int duration;
    private Date aired;
    private boolean filler;
    private boolean recap;
    private String synopsis;
}
