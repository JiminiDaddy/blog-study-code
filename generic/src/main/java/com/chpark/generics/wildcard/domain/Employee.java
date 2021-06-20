package com.chpark.generics.wildcard.domain;

public class Employee implements Comparable<Employee> {
	private String name;
	private int salary;

	@Override
	public int compareTo(Employee employee) {
		return Integer.compare(this.salary, employee.salary) * -1;
	}

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
