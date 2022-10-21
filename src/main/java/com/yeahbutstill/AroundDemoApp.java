package com.yeahbutstill;

import com.yeahbutstill.config.DemoConfig;
import com.yeahbutstill.service.TraffiFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AroundDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		TraffiFortuneService fortuneService = context.getBean("traffiFortuneService", TraffiFortuneService.class);

		System.out.println("\nMain Program: AroundDemoApp");
		System.out.println("Calling getFortune");

		String data = fortuneService.getFortune();

		System.out.println("\nMy fortune is: " + data);

		System.out.println("Finished");

		// close the context
		context.close();

	}

}
