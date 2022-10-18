package com.yeahbutstill.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    @Pointcut("execution(* com.yeahbutstill.dao.*.*(..))")
    public void forDaoPackage(){}

    // create pointcut for getter methods
    @Pointcut("execution(* com.yeahbutstill.dao.*.get*(..))")
    public void getter() {}

    // create pointcut for setter methods
    @Pointcut("execution(* com.yeahbutstill.dao.*.set*(..))")
    public void setter() {}

    // create point: include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}

}
