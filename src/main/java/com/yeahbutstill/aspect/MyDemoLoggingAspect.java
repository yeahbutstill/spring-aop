package com.yeahbutstill.aspect;

import com.yeahbutstill.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices to logging

    // let's start with annotation @Before advice

    @Before("com.yeahbutstill.aop.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n===>>> Executing @Before advice on addAccount()");
    }

    @Before("com.yeahbutstill.aop.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdviceSignature(JoinPoint joinPoint) {

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);
    }

    @Before("com.yeahbutstill.aop.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdviceArguments(JoinPoint joinPoint) {

        // display the method arguments
        // get args
        Object[] args = joinPoint.getArgs();

        // loop through args
        for (Object tempArg : args) {
            System.out.println(tempArg);
            if (tempArg instanceof Account) {
                // downcast and print account specific stuff
                Account account = (Account) tempArg;
                System.out.println("account name: " + account.getName());
                System.out.println("account level: " + account.getLevel());
            }
        }
    }

    // add a new advices for @AfterReturning on the findAccounts method
    @AfterReturning(pointcut = "execution(* com.yeahbutstill.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n====>>> result is: " + method);

        // let's post-process the data ... let's modify it :)
        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);
        System.out.println("\n====>>> result is: " + method);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through account
        for (Account account : result) {

            // get uppercase versions of name
            String upperName = account.getName().toUpperCase();

            // update the name on the account
            account.setName(upperName);

        }

    }

    @After("execution(* com.yeahbutstill.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=======>>>> Executing @After (finally) on method: " + method);

    }

    @AfterThrowing(pointcut = "execution(* com.yeahbutstill.dao.AccountDAO.findAccounts(..))",
            throwing = "throwable")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable throwable) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=======>>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("\n=========>>> The exception is: " + throwable);

    }

}
