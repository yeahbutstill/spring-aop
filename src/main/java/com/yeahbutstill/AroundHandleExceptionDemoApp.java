package com.yeahbutstill;

import com.yeahbutstill.config.DemoConfig;
import com.yeahbutstill.service.TraffiFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundHandleExceptionDemoApp {

	private static Logger logger = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		TraffiFortuneService fortuneService = context.getBean("traffiFortuneService", TraffiFortuneService.class);

		logger.info("\nMain Program: AroundDemoApp");
		logger.info("Calling getFortune");

		Boolean tripWire = true;
		String data = fortuneService.getFortune(tripWire);

		logger.info("\nMy fortune is: " + data);

		logger.info("Finished");

		// close the context
		context.close();

	}

}
