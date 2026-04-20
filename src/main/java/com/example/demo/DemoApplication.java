package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * This is not a production-ready project and does not follow best practices;
 * it is intended only as a demonstration of adding persistence with Spring Data JPA.
 */
@SpringBootApplication
public class DemoApplication {

    static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
