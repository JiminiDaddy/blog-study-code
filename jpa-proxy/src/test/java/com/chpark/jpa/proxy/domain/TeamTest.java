package com.chpark.jpa.proxy.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@DataJpaTest
class TeamTest {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@BeforeEach
	void setUp() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Team team = new Team("바르샤");

		Member member1 = new Member("메시", team);
		Member member2 = new Member("푸욜", team);
		Member member3 = new Member("사비", team);
		team.addMember(member1);
		team.addMember(member2);
		team.addMember(member3);

		entityManager.persist(team);

		entityManager.getTransaction().begin();
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Test
	@DisplayName("팀=부모, 멤버=자식일 때 전이가 잘 되는지 확인")
	void cascadingTeamAndMember() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Team team = entityManager.find(Team.class, 1L);
		Assertions.assertThat(team).isNotNull();

		System.out.println("team name: " + team.getName() + " members_size: " + team.getMembers().size());
		for (Member member : team.getMembers()) {
			Assertions.assertThat(member).isNotNull();
			System.out.println("member: " + member.getName());
		}

		entityManager.close();
	}
}
