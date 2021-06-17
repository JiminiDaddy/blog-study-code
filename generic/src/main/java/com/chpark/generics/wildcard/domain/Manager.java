package com.chpark.generics.wildcard.domain;

public class Manager extends Employee {
	public Manager(String name) {
		super(name);
	}

	public Manager(String name, int salary) {
		super(name, salary);
	}
}
