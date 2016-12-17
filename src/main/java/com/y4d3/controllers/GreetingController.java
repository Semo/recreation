package com.y4d3.controllers;

import com.y4d3.services.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by semo on 10.12.16.
 */
@Controller
public class GreetingController {

    private HelloWorldService helloWorldService;

    @Autowired
    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public String sayHello() {
        String greeting = helloWorldService.getGreeting();

        System.out.println(greeting);
        return greeting;
    }


}
