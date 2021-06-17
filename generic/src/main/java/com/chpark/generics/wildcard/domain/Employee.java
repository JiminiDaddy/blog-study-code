package com.chpark.generics.wildcard.domain;

public class Employee {
	private String name;
	private int salary;

	public Employee(String name) {
		this.name = name;
	}

	public Employee(String name, int salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public int getSalary() {
		return salary;
	}
}
