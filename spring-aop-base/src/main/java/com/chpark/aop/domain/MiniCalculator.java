package com.chpark.aop.domain;

import org.springframework.stereotype.Component;

@Component
public class MiniCalculator implements Calculator {

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
