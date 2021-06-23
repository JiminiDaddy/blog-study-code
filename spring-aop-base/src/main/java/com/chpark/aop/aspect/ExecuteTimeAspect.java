package com.chpark.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ExecuteTimeAspect {

	//@Pointcut("execution(public * com.chpark.aop.domain..*(..))")
	// MiniCalculator만 AOP가 적용되도록 Pointcut 지정
	@Pointcut("execution(public * com.chpark.aop.domain.MiniCalculator.*(..))")
	private void publicTarget() {

	}

	// 소요시간 측정
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long finish = System.currentTimeMillis();
		Signature signature = joinPoint.getSignature();
		System.out.printf("%s.%s(%s)실행 시간: %d ms\n",
			joinPoint.getTarget().getClass().getSimpleName(),
			signature.getName(),
			Arrays.toString(joinPoint.getArgs()),
			(finish - start)
		);
		return result;
	}
}
