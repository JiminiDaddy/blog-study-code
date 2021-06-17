package com.chpark.generics.wildcard.domain;

public class HighSchoolStudentInfo extends StudentInfo {
	private int grade;

	public HighSchoolStudentInfo(String name, String schoolName, int grade) {
		super(name, schoolName);
		this.grade = grade;
	}

	public int getGrade() {
		return grade;
	}
}
