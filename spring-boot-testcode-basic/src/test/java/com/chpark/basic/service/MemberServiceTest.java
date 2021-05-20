package com.chpark.basic.service;

import com.chpark.basic.domain.MemberRepository;
import com.chpark.basic.web.dto.MemberFindResponseDto;
import com.chpark.basic.web.dto.MemberJoinRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2021/05/20
 * Time : 3:38 PM
 */

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("join기능이 제대로 동작하는지 확인")
    void join() {
        // given
        MemberJoinRequestDto requestDto = new MemberJoinRequestDto("chpark", 34);
        when(memberRepository.save(any())).thenReturn(requestDto.toEntity());
        MemberJoinRequestDto requestDto2 = new MemberJoinRequestDto("tester", 20);
        when(memberRepository.save(any())).thenReturn(requestDto2.toEntity());

        // when
        memberService.join(requestDto);
        memberService.join(requestDto);

        // then
        // Id 생성 전략을 Identity를 사용하므로, 실제 DBd에 저장되야만 Id가 생성된다. 따라서 테스트에서 Id를 검증할 수 없다.
        // 만약 Id를 검증하려면 Repository를 Mock이 아니라 실제 Bean으로 사용해야 가능할 듯 싶다.
    }

    @Test
    @DisplayName("find기능이 제대로 동작하는지 확인")
    void find() {
        // given
        MemberJoinRequestDto requestDto = new MemberJoinRequestDto("chpark", 34);
        when(memberRepository.save(any())).thenReturn(requestDto.toEntity());
        memberService.join(requestDto);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(requestDto.toEntity()));

        // when
        MemberFindResponseDto responseDto = memberService.find(1L);

        // then
        Assertions.assertThat(responseDto).isNotNull();
        Assertions.assertThat(responseDto.getName()).isEqualTo("chpark");
        Assertions.assertThat(responseDto.getAge()).isEqualTo(34);
    }
}
