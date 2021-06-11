package com.chpark.access.user;

public class Employee extends User {
	private String number;

	public Employee(int age) {
		setAge(age);
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	protected void setAge(int age) {
		super.setAge(age + 10);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	void setEmail(String email) {
		super.setEmail(email);
	}

	public String getNumber() {
		return number;
	}
}
