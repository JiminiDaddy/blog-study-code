package com.chpark.generics.wildcard.type;

import com.chpark.generics.wildcard.domain.Employee;
import com.chpark.generics.wildcard.domain.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class WithTypeApplication {
	public static void main(String[] args) {
		Manager[] managers = new Manager[3];
		managers[0] = new Manager("user1", 1_000_000);
		managers[1] = new Manager("user2", 3_000_000);
		managers[2] = new Manager("user3", 5_000_000);

		printAll(managers, (a) -> a.getSalary() > 3_000_000);

		// Manager는 Employee를 상속받았으므로, Comparable<Employee>를 따라가게된다.
		Arrays.sort(managers);
		for (Employee m : managers) {
			System.out.println("m_name: " + m.getName() + ", salary: " + m.getSalary());
		}

		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(100);
		numbers.add(320);
		numbers.add(550);
		swap(numbers, 1, 2);
		for (Integer number: numbers) {
			System.out.println("number: " + number);
		}

	}

	private static <T> void printAll(T[] elements, Predicate<T> filter) {
		for (T element : elements) {
			if (filter.test(element)) {
				System.out.println("filtered elements:" + element.toString());
			}
		}
	}

	private static void swap(ArrayList<?> elements, int i, int j) {
		// ? 와일드카드는 타입 인자로 사용될 순 있지만, 타입으로는 사용할 수 없다.
		/*
		? temp = elements.get(i);
		elements.set(i, elements.get(j));
		elements.set(j, temp);
		*/
		swapHelper(elements, i, j);
	}

	private static <T> void swapHelper(ArrayList<T> elements, int i, int j) {
		// 와일듴카드를 제네릭으로 변환한 Helper를 사용하면, 외부에서는 제네릭에 대해 알 필요없이 와일드 카드를 이용해 호출이 가능해진다.
		T temp = elements.get(i);
		elements.set(i, elements.get(j));
		elements.set(j, temp);
	}
}
