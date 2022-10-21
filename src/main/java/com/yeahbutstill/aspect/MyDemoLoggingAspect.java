package com.yeahbutstill.aspect;

import com.yeahbutstill.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    // this is where we add all of our related advices to logging

    // let's start with annotation @Before advice

    @Before("com.yeahbutstill.aop.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        logger.info("\n===>>> Executing @Before advice on addAccount()");
    }

    @Before("com.yeahbutstill.aop.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdviceSignature(JoinPoint joinPoint) {

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.info("Method: " + methodSignature);
    }

    @Before("com.yeahbutstill.aop.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdviceArguments(JoinPoint joinPoint) {

        // display the method arguments
        // get args
        Object[] args = joinPoint.getArgs();

        // loop through args
        for (Object tempArg : args) {
            logger.info(tempArg.toString());
            if (tempArg instanceof Account) {
                // downcast and print account specific stuff
                Account account = (Account) tempArg;
                logger.info("account name: " + account.getName());
                logger.info("account level: " + account.getLevel());
            }
        }
    }

    // add a new advices for @AfterReturning on the findAccounts method
    @AfterReturning(pointcut = "execution(* com.yeahbutstill.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        logger.info("\n====>>> result is: " + method);

        // let's post-process the data ... let's modify it :)
        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);
        logger.info("\n====>>> result is: " + method);

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
        logger.info("\n=======>>>> Executing @After (finally) on method: " + method);

    }

    @AfterThrowing(pointcut = "execution(* com.yeahbutstill.dao.AccountDAO.findAccounts(..))",
            throwing = "throwable")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable throwable) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=======>>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        logger.info("\n=========>>> The exception is: " + throwable);

    }

    @Around("execution(* com.yeahbutstill.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        logger.info("\n===============>>> executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {

            // log the exception
            logger.warning(e.getMessage());

            // rethrowing exception
            throw e;
        }

        // get end timestamp
        Long end = System.currentTimeMillis();

        // compute duration and display it
        Long duration = end - begin;
        logger.info("\n=========>>> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

}
