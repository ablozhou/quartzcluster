package com.abloz;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
	Logger log = LoggerFactory.getLogger(MainApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
