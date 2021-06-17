package com.chpark.generics.wildcard.subtype;

import com.chpark.generics.wildcard.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class SubTypeApplication {
	// List<? extends Employee>에 들어있는 요소들은 모두 Employee거나 상속받은 객체들이다.
	// 하지만 이 List에 Employee나 상속받은 객체들을 요소로 추가할 수는 없다.
	// Employee를 상속받는 누군가이기때문에, Employee나 Manager라던가... 누가 올지 모른다.

	// 만약 여기서 Employee를 상속받는 Manager를 추가하는것이 허용된다고 할 때,
	// staffs의 요소가 Employee를 상속받는 Owner 라면?
	// 서로 다른 타입이 하나의 List에 들어가므로 말이 안되는 상황이 발생할 수 있다.

	// 즉 메서드 인자로 ? extends 형태의 제네릭을 사용하면 ReadOnly로 사용할 수 있는 특징이 생긴다!!

	private static void printNames(List<? extends Employee> staffs) {
		for (Employee employee : staffs) {
			System.out.println("employee name: " + employee.getName());
			//staffs.add(employee);		// Compile Error!
		}
	}

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("chpark"));
		employees.add(new Employee("user1"));
		employees.add(new Employee("user2"));

		printNames(employees);
	}
}
