package com.chpark.access.school;

import com.chpark.access.user.User;

public class Student extends User {
	private String schoolName;

	public Student(int age) {
		setAge(age);
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	protected void setAge(int age) {
		super.setAge(age - 10);
	}
}
//public class Student extends User {
//}
