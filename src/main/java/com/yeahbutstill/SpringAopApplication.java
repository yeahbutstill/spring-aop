package com.yeahbutstill;

import com.yeahbutstill.config.DemoConfig;
import com.yeahbutstill.dao.AccountDAO;
import com.yeahbutstill.dao.MembershipDAO;
import com.yeahbutstill.entity.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootApplication
public class SpringAopApplication {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

		// get membership bean from spring container
		MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

		// create new object
		Account account = new Account();
		account.setName("Dani");
		account.setLevel("Platinum");

		// call the business method again
		accountDAO.addAccount(account, true);
		accountDAO.doWork();
		membershipDAO.addAccount();
		membershipDAO.goToSleep();
		accountDAO.setName("foo");
		accountDAO.setServiceCode("bar");

		String name = accountDAO.getName();
		String code = accountDAO.getServiceCode();

		// close the context
		context.close();
		
//		SpringApplication.run(SpringAopApplication.class, args);
	}

}
