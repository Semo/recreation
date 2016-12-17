package com.y4d3.services;

import org.springframework.stereotype.Component;

/**
 * Created by semo on 10.12.16.
 */

@Component
public class HelloWorldServiceEnglish implements HelloWorldService {
    @Override
    public String getGreeting() {
        return "Hello World";
    }
}
