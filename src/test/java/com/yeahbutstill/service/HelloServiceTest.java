package com.yeahbutstill.service;

import com.yeahbutstill.service.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HelloService.class)
class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void testHelloService() {

        Assertions.assertEquals("Hello Dani", helloService.hello("Dani"));
        Assertions.assertEquals("Bye Dani", helloService.bye("Dani"));

    }

}
