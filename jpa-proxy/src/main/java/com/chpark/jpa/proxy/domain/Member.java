package com.chpark.jpa.proxy.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	// fetch 전략은 일반 객체(1:1)의경우 EAGER을, 컬렉션인 경우(1:N) Lazy를 기본값으로 사용한다.
	//@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Team team;

	public Member(String name, Team team) {
		this.name = name;
		this.team = team;
	}
}
