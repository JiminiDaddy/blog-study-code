package com.chpark.basic.web.dto;

import com.chpark.basic.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberJoinResponseDto {
    private Long id;

    private String name;

    private Integer age;

    public MemberJoinResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
    }
}
