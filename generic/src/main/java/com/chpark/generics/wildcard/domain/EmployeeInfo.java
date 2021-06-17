package com.chpark.generics.wildcard.domain;

public class EmployeeInfo extends PersonInfo{
	private int salary;

	public EmployeeInfo(String name, int salary) {
		super(name);
		this.salary = salary;
	}

	public int getSalary() {
		return salary;
	}
}
