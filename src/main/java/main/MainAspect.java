package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import chap07.RecCalculator;
import config.AppCtx;

public class MainAspect {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		// Calculator cal = ctx.getBean("calculator", Calculator.class);
		RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
		long fiveFact = cal.factorial(5);
		System.out.printf("cal.factorial(5) = %d%n", fiveFact);
		System.out.println(cal.getClass().getName());
		ctx.close();

		/*try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);) {
			Calculator cal = ctx.getBean("calculator", Calculator.class);
			long fiveFact = cal.factorial(5);
			System.out.printf("cal.factorial(5) = %d%n", fiveFact);
			System.out.println(cal.getClass().getName());
		
		}*/

	}

}
