package com.chpark.aop.domain;

import org.springframework.stereotype.Component;

//@Primary		// 같은 타입의 Bean이 등록되어 중복 예외가 발생할 경우 우선순위를 결정해준다. (Primary가 우선됨)
//@Qualifier("noAspectCalculator")		// Qualifier는 구분자를 지정해주는 것일 뿐, Bean Name을 변경해주지는 않는다.
@Component
public class NoAspectCalculator implements Calculator{
	@Override
	public int sum(int start, int end) {
		int result = 0;
		for (int i = start; i <= end; ++i) {
			try {
				result += i;
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
