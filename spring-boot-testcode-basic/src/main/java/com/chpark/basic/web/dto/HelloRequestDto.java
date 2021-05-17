package com.chpark.basic.web.dto;

import lombok.Getter;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2021/05/17
 * Time : 1:03 AM
 */

@Getter
public class HelloRequestDto {
    private String name;

    protected HelloRequestDto() { }

    public HelloRequestDto(String name) {
        this.name = name;
    }
}
