package com.thefirstrow.dreammate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DreamMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamMateApplication.class, args);
	}

}
