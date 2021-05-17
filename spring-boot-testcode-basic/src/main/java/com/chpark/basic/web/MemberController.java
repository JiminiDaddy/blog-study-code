package com.chpark.basic.web;

import com.chpark.basic.service.MemberService;
import com.chpark.basic.web.dto.MemberFindResponseDto;
import com.chpark.basic.web.dto.MemberJoinRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2021/05/17
 * Time : 9:55 PM
 */

@Slf4j
@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/{id}")
    public MemberFindResponseDto findMember(@PathVariable(value = "id") Long memberId) {
        log.debug("findMember, id:<{}>", memberId);
        MemberFindResponseDto responseDto = memberService.find(memberId);
        log.info("findMember, name:<{}>", responseDto.getName());
        return responseDto;
    }

    @PostMapping("/member")
    public Long joinMember(@RequestBody MemberJoinRequestDto requesteDto) {
        log.info("joinMember, name:<{}>, age:<{}>", requesteDto.getName(), requesteDto.getAge());
        Long id = memberService.join(requesteDto);
        log.info("joinMember, id:<{}>", id);
        return id;
    }
}
