package com.chpark.access.user;

public class User {
	private String name;

	private int age;

	private String address;

	private String email;

	public void setName(String name) {
		this.name = name;
	}

	protected void setAge(int age) {
		this.age = age;
	}

	private void setAddress(String address) {
		this.address = address;
	}

	void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}
}
