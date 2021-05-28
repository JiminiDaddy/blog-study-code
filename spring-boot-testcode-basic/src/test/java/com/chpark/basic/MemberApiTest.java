package com.chpark.basic;

import com.chpark.basic.domain.MemberRepository;
import com.chpark.basic.web.dto.MemberFindResponseDto;
import com.chpark.basic.web.dto.MemberJoinRequestDto;
import com.chpark.basic.web.dto.MemberJoinResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberApiTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MemberRepository memberRepository;

    private MemberJoinRequestDto requestDto;
    private MemberJoinRequestDto requestDto2;

    @BeforeEach
    void setUp() {
        requestDto  = new MemberJoinRequestDto("user1", 24);
        requestDto2 = new MemberJoinRequestDto("user2", 40);
    }

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("[통합]회원 가입하기")
    void joinMember() {
        MemberJoinResponseDto responseDto = testRestTemplate.postForObject(
                "/member", requestDto, MemberJoinResponseDto.class);

        Assertions.assertThat(responseDto.getName()).isEqualTo("user1");
        Assertions.assertThat(responseDto.getAge()).isEqualTo(24);
    }

    @Test
    @DisplayName("[통합]회원 조회하기")
    void findMember() {
        // given
        MemberJoinResponseDto joinResponseDto = testRestTemplate.postForObject(
                "/member", requestDto, MemberJoinResponseDto.class);
        MemberJoinResponseDto joinResponseDto2 = testRestTemplate.postForObject(
                "/member", requestDto2, MemberJoinResponseDto.class);

        // when
        MemberFindResponseDto findResponseDto = testRestTemplate.getForObject(
                "/member/" + joinResponseDto.getId(), MemberFindResponseDto.class);
        MemberFindResponseDto findResponseDto2 = testRestTemplate.getForObject(
                "/member/" + joinResponseDto2.getId(), MemberFindResponseDto.class);

        Assertions.assertThat(findResponseDto.getAge()).isEqualTo(joinResponseDto.getAge());
        Assertions.assertThat(findResponseDto.getName()).isEqualTo(joinResponseDto.getName());

        Assertions.assertThat(findResponseDto2.getAge()).isEqualTo(joinResponseDto2.getAge());
        Assertions.assertThat(findResponseDto2.getName()).isEqualTo(joinResponseDto2.getName());
    }
}
