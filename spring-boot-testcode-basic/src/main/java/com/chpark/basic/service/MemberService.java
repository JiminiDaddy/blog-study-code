package com.chpark.basic.service;

import com.chpark.basic.domain.Member;
import com.chpark.basic.domain.MemberRepository;
import com.chpark.basic.web.dto.MemberFindResponseDto;
import com.chpark.basic.web.dto.MemberJoinRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2021/05/17
 * Time : 9:57 PM
 */

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberFindResponseDto find(final Long memberId) {
       Member member = memberRepository.findById(memberId).orElseThrow(
               () -> new IllegalArgumentException("Cannot found member, WrongId:<" +  memberId + ">"));
       return new MemberFindResponseDto(member);
    }

    public Long join(final MemberJoinRequestDto requesteDto) {
        Member member = memberRepository.save(requesteDto.toEntity());
        return member.getId();
    }
}
