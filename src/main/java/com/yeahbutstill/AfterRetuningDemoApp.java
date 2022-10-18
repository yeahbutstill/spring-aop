package com.yeahbutstill;

import com.yeahbutstill.config.DemoConfig;
import com.yeahbutstill.dao.AccountDAO;
import com.yeahbutstill.entity.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterRetuningDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

		// call method to find the account
		List<Account> accountList = accountDAO.findAccounts();

		// display the accounts
		System.out.println("\n\nMain program: AfterReturnDemoApp");
		System.out.println("-----");

		System.out.println(accountList);
		System.out.println("\n");

		// close the context
		context.close();

	}

}
