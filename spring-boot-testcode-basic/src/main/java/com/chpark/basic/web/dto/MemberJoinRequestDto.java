package com.chpark.basic.web.dto;

import com.chpark.basic.domain.Member;
import lombok.Getter;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2021/05/17
 * Time : 9:57 PM
 */

@Getter
public class MemberJoinRequestDto {
    private String name;

    private Integer age;

    public MemberJoinRequestDto(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Member toEntity() {
       return Member.builder().name(name).age(age).build();
    }
}
