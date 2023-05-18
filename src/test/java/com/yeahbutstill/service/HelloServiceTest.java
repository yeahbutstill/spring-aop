package com.yeahbutstill.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloServiceTest {

    private final HelloService helloService;

    @Autowired
    HelloServiceTest(HelloService helloService) {
        this.helloService = helloService;
    }

    @Test
    void testHelloService() {

        Assertions.assertEquals("Hello Dani", helloService.hello("Dani"));
        Assertions.assertEquals("Bye Dani", helloService.bye("Dani"));

        helloService.test();
    }

}
