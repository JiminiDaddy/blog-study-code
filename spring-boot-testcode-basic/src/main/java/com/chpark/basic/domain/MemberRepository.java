package com.chpark.basic.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2021/05/17
 * Time : 9:58 PM
 */

public interface MemberRepository extends JpaRepository<Member, Long> {
}
