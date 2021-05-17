package com.chpark.basic.web;

import com.chpark.basic.service.MemberService;
import com.chpark.basic.web.dto.MemberFindResponseDto;
import com.chpark.basic.web.dto.MemberJoinRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2021/05/17
 * Time : 11:18 PM
 */

@WebMvcTest
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 확인")
    void joinMember() throws Exception {
        MemberJoinRequestDto requesteDto = new MemberJoinRequestDto("chpark", 34);

        // when
        // any : 어떤 타입으로 입력이 들어오든 넘어가기위해 설정
        when(memberService.join(any())).thenReturn(1000L);

        mockMvc.perform(post("/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requesteDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("1000"))
                .andDo(print());
    }

    @Test
    @DisplayName("회원조회 확인")
    void findMember() throws Exception {
        MemberFindResponseDto responseDto = new MemberFindResponseDto(new MemberJoinRequestDto("chpark", 34).toEntity());

        when(memberService.find(1L)).thenReturn(responseDto);

        MvcResult result = mockMvc.perform(get("/member/1"))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();
        DocumentContext documentContext = JsonPath.parse(result.getResponse().getContentAsString());
        Assertions.assertThat((String)documentContext.read("name")).isEqualTo("chpark");
        Assertions.assertThat((int)documentContext.read("age")).isEqualTo(34);
    }
}
