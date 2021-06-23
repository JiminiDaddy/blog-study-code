package com.chpark.aop;

import com.chpark.aop.domain.Calculator;
import com.chpark.aop.domain.MiniCalculator;
import com.chpark.aop.domain.NoAspectCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class MainApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(MainApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		ApplicationContext context = application.run(args);

		//Calculator calculator = context.getBean(Calculator.class);
		System.out.println("\nUse Spring-AOP");
		Calculator calculator = context.getBean(MiniCalculator.class);
		int result = calculator.sum(1, 1000);
		System.out.println("result: " + result);
		System.out.println("bean: " + calculator.getClass().getName());

		System.out.println("\nNot Use Spring-AOP");
		Calculator noAspectCalculator = context.getBean(NoAspectCalculator.class);
		result = noAspectCalculator.sum(1, 1000);
		System.out.println("result: " + result);
		System.out.println("bean: " + noAspectCalculator.getClass().getName());

	}
}
