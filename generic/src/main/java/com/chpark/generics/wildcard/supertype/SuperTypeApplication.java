package com.chpark.generics.wildcard.supertype;

import com.chpark.generics.wildcard.domain.Employee;
import com.chpark.generics.wildcard.domain.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SuperTypeApplication {
	public static void main(String[] args) {
		Manager[] employees = new Manager[3];
		employees[0] = new Manager("chpark", 5_000_000);
		employees[1] = new Manager("park", 3_000_000);
		employees[2] = new Manager("kim", 2_000_000);

		System.out.println("salary가 3,500,000이 넘는 직원목록");
		printAll(employees, (employee -> employee.getSalary() > 3_500_000));

		System.out.println("salary가 3,500,000이 안넘는 직원목록");
		printAll(employees, Predicate.not(employee -> employee.getSalary() > 3_500_000));

		//System.out.println("Employee -> Object casting후 목록조회");
		//printAll(employees, (employee -> employee.toString().length() % 2 == 0));

		List<Integer> integers = new ArrayList<>();
		integers.add(10);
		integers.add(30);
		integers.add(50);

		List<? super Integer> integers2 = integers;
		integers2.add(5000);

		// integers2의 요소 타입이 <? super Integer> 이므로 Integer나 그 상위 클래스는 모두 가능하다.
		// Integer인지, Number인지 Object인지 알 수 없다.
		// 따라서 integers2의 요소를 어떤 타입으로 가져와야할지 모르므로, Object로만 받을 수 있다.
		/*	요소들을 탐색할 때 타입을 Integer로 받을 수 없다. 따라서 아래 for문은 compile error가 발생한다.
		for (Integer integer: integers2) {
			System.out.println("integers2_item: " + integer);
		}
		*/
		// Object는 최상위 클래스이므로, 타입으로 받을 수 있다.
		// 함수형 인터페이스를 메서드 파라미터로 사용할 때 주로 사용한다.
		for (Object integer: integers2) {
			System.out.println("integers2_item: " + integer);
		}
	}

	private static void printAll(Manager[] employees, Predicate<? super Employee> filter) {
		for (Manager employee : employees) {
			if (filter.test(employee)) {
				System.out.println("filtered employee name: " + employee.getName());
			}
		}
	}
}
