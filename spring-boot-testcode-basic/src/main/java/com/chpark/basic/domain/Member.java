package com.chpark.basic.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2021/05/17
 * Time : 10:04 PM
 */

@NoArgsConstructor
@Getter
@Table(name = "members")
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "age", nullable = true)
    private Integer age;

    @Builder
    public Member(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
