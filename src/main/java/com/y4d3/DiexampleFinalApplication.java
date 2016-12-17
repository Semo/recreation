package com.y4d3;

import com.y4d3.controllers.GreetingController;
import com.y4d3.controllers.ProductController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiexampleFinalApplication {

    public static void main(String[] args) {
// 	ApplicationContext ctx
        SpringApplication.run(DiexampleFinalApplication.class, args);

        //GreetingController controller = (GreetingController) ctx.getBean("greetingController");
        //controller.sayHello();

        //    ProductController productController = (ProductController) ctx.getBean("productController");
    }
}
