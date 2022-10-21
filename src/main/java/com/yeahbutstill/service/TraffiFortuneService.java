package com.yeahbutstill.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TraffiFortuneService {
    
    public String getFortune() {
        // simulate a delay
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // return a fortune
        return "Expect heavy traffic this morning";

    }

    public String getFortune(Boolean tripWire) {

        if (tripWire) {
            throw new RuntimeException("Major accident! highway is closed");
        }

        return getFortune();
    }
}
