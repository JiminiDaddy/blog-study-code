package com.chpark.jpa.proxy.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Transactional
@DataJpaTest
class MemberTest {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	private Long memberId;

	private Long teamId;

	@BeforeEach
	void setUp() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Team team = new Team("토트넘");
		Member member = new Member("손흥민", team);
		team.addMember(member);

		entityManager.persist(team);

		entityManager.getTransaction().begin();
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();

		teamId = team.getId();
		memberId = member.getId();
	}

	@Test
	@DisplayName("회원과 팀 정보 출력")
	void printUserAndTeam() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Member member = entityManager.find(Member.class, memberId);
		Assertions.assertThat(member).isNotNull();
		Team team = member.getTeam();
		Assertions.assertThat(team).isNotNull();
		Assertions.assertThat(team.getId()).isEqualTo(teamId);

		System.out.println("member가 실제 엔티티인가? " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(member));
		System.out.println("team이 실제 엔티티인가? " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(team));

		System.out.println("생성된 member의 인스턴스 이름: " + member.getClass().getName());
		System.out.println("생성된 team의 인스턴스 이름: " + team.getClass().getName());
		Assertions.assertThat(member.getName()).isEqualTo("손흥민");
		Assertions.assertThat(member.getTeam().getName()).isEqualTo("토트넘");

		entityManager.close();
	}
}
