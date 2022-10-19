package com.yeahbutstill;

import com.yeahbutstill.config.DemoConfig;
import com.yeahbutstill.dao.AccountDAO;
import com.yeahbutstill.entity.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterThrowingDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

		// call method to find the account
		List<Account> accountList = null;
		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;
			accountList = accountDAO.findAccounts(tripWire);
		} catch (Exception e) {
			System.out.println("\n\nMain program ... caught exception: " + e);
		}


		// display the accounts
		System.out.println("\n\nMain program: AfterThrowingDemoApp");
		System.out.println("-----");

		System.out.println(accountList);
		System.out.println("\n");

		// close the context
		context.close();

	}

}
