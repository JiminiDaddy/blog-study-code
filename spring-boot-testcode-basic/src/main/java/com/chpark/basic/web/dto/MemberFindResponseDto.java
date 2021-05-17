package com.chpark.basic.web.dto;

import com.chpark.basic.domain.Member;
import lombok.Getter;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2021/05/17
 * Time : 9:56 PM
 */

@Getter
public class MemberFindResponseDto {
    private Long id;

    private String name;

    private Integer age;

    public MemberFindResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
    }

}
