package com.chpark.basic.web;

import com.chpark.basic.web.dto.HelloRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2021/05/17
 * Time : 1:02 AM
 */

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello")
    public String hello(@RequestBody HelloRequestDto requestDto) {
        System.out.println(requestDto.getName());
        return "hello " + requestDto.getName();
    }
}
