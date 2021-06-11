package com.chpark.access;

import com.chpark.access.school.Student;
import com.chpark.access.user.Employee;
import com.chpark.access.user.User;

public class AccessModifierApp {
	public static void main(String[] args) {
		User user = new User();
		user.setName("chpark");
		//user.setAge(20);						// protected는 상속받지 않은 클래스에선 접근 불가
		//user.setEmail("chpark@test.com");		// default는 외부 패키지에서 접근 불가
		//user.setAddress("Suwon");				// private는 동일 클래스 외에서는 접근 불가

		Employee employee = new Employee(20);
		employee.setName("test");
		System.out.println(employee.getName());
		//employee.setAge(50);					// protected는 상속받지 않은 클래스에선 접근 불가
		System.out.println("employee age: 20 to " + employee.getAge());

		Student student = new Student(20);
		System.out.println("student age: 20 to " + student.getAge());

	}
}
