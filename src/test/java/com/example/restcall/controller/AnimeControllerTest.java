package com.example.restcall.controller;

import com.example.restcall.config.JGivenConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({JGivenConfig.class})
@AutoConfigureMockMvc
@SpringBootTest
public class AnimeControllerTest {

}
