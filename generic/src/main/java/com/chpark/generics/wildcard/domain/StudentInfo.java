package com.chpark.generics.wildcard.domain;

public class StudentInfo extends PersonInfo {
	private String schoolName;

	public StudentInfo(String name, String schoolName) {
		super(name);
		this.schoolName = schoolName;
	}

	public String getSchoolName() {
		return schoolName;
	}
}
