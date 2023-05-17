package com.yeahbutstill.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Pointcut("target(com.yeahbutstill.service.HelloService)")
    public void helloServiceMethod() {}

}
