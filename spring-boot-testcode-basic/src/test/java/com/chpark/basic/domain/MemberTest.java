package com.chpark.basic.domain;

import com.chpark.basic.web.dto.MemberJoinRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2021/05/20
 * Time : 2:33 PM
 */

@DataJpaTest
public class MemberTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("멤버가 DB에 저장이 잘 되는지 확인")
    void saveMember() {
        // given
        Member member = new MemberJoinRequestDto("chpark", 34).toEntity();

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Assertions.assertThat(member).isSameAs(savedMember);
        Assertions.assertThat(member.getName()).isEqualTo(savedMember.getName());
        Assertions.assertThat(savedMember.getId()).isNotNull();
        Assertions.assertThat(memberRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("저장된 멤버가 제대로 조회되는지 확인")
    void findMember() {
        // given
        Member savedMember = memberRepository.save(new MemberJoinRequestDto("chpark", 34).toEntity());
        Member savedMember2 = memberRepository.save(new MemberJoinRequestDto("tester", 20).toEntity());

        // when
        Member findMember = memberRepository.findById(savedMember.getId())
                .orElseThrow(() -> new IllegalArgumentException("Wrong MemberId:<" + savedMember.getId() + ">"));

        Member findMember2 = memberRepository.findById(savedMember2.getId())
                .orElseThrow(() -> new IllegalArgumentException("Wrong MemberId:<" + savedMember2.getId() + ">"));

        // then
        Assertions.assertThat(memberRepository.count()).isEqualTo(2);

        Assertions.assertThat(findMember.getName()).isEqualTo("chpark");
        Assertions.assertThat(findMember.getAge()).isEqualTo(34);

        Assertions.assertThat(findMember2.getName()).isEqualTo("tester");
        Assertions.assertThat(findMember2.getAge()).isEqualTo(20);
    }
}
