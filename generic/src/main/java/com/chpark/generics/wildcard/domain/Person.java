package com.chpark.generics.wildcard.domain;

// Generic Type을 StudentInfo 클래스의 상위 제한시킴 (StudentInfo 포함 하위 클래스만 사용 가능)
public class Person <T extends StudentInfo> {
	private T info;

	public Person(T info) {
		this.info = info;
	}

	public void printInfo() {
		System.out.println(info.getName() + " " + info.getSchoolName());
	}
}
