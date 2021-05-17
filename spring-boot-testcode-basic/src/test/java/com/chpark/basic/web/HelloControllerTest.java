package com.chpark.basic.web;

import com.chpark.basic.web.dto.HelloRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2021/05/17
 * Time : 1:04 AM
 */

@SpringBootTest
@AutoConfigureMockMvc   // MockMvc를 사용하려면 필수 선언
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)     // default = MOCK
public class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("hello Get method가 잘 요청되는지 확인")
    void checkHelloGet() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"))
                .andDo(print());
    }

    @Test
    @DisplayName("hello Post method가 잘 요청되는지 확인")
    void checkHelloPost() throws Exception {
        HelloRequestDto requestDto = new HelloRequestDto("chpark");

        mockMvc.perform(post("/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("hello chpark"))
                .andDo(print());
    }
}
