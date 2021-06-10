package com.chpark.jpa.proxy.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "team", orphanRemoval = true)
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
	private List<Member> members;

	public Team(String name) {
		this.name = name;
		this.members = new ArrayList<>();
	}

	public void addMember(Member member) {
		this.members.add(member);
	}
}
