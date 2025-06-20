package com.blockbusterfake.demoT2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.blockbusterfake.demoT2", "com.blockbusterfake.demoT2.controller"})
public class DemoT2Application {
    public static void main(String[] args) {
        SpringApplication.run(DemoT2Application.class, args);
    }
}