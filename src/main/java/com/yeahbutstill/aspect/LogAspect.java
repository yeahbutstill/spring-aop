package com.yeahbutstill.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("target(com.yeahbutstill.service.HelloService)")
    public void helloServiceMethod() {}

    @Before("helloServiceMethod()")
    public void beforeHelloServiceMethod1() {
        log.info("Before HelloService Method");
    }

    @Before("helloServiceMethod()")
    public void beforeHelloServiceMethod2() {
        log.info("Again Before HelloService Method");
    }

    @Before("helloServiceMethod()")
    public void beforeHelloServiceMethod3(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Before className {}. methodName {}", className, methodName);
    }

    @Around("helloServiceMethod()")
    public Object aroundHelloServiceMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        try {
            log.info("Around Before className {}. methodName {}", className, methodName);
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable e) {
            log.info("Around Error className {}. methodName {}", className, methodName);
            throw e;
        } finally {
            log.info("Around Finally className {}. methodName {}", className, methodName);
        }

    }

    @Pointcut("execution(* com.yeahbutstill.service.HelloService.*(java.lang.String))")
    public void pointcutHelloServiceStringParam() {}

    @Before("pointcutHelloServiceStringParam()")
    public void logStringParameter(JoinPoint joinPoint) {
        String value = (String) joinPoint.getArgs()[0];
        log.info("Execute method with parameter {}", value);
    }

    @Pointcut("execution(* com.yeahbutstill.service.*.*(..))")
    public void pointcutServicePackage() {}

    @Pointcut("bean(*Service)")
    public void pointcutServiceBean() {}

    @Pointcut("execution(public * *(..))")
    public void pointcutPublicMethod() {}

    @Pointcut("pointcutServicePackage() && pointcutServiceBean() && pointcutPublicMethod()")
    public void publicMethodForService() {}

    @Before("publicMethodForService()")
    public void logAllServiceMethod() {
        log.info("Log All Service Method");
    }

}
