package com.guru.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan("com.guru")
public class Application {

    public static void main(String[] args) throws Throwable {
    	//System.setProperty("server.port", "8082");
    	
        SpringApplication.run(Application.class, args);
    }

}
