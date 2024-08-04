package com.example.anotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("")
public class AnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnotationApplication.class, args);
	}

}
