package com.sistemapacto.server;

import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemapactoServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(SistemapactoServerApplication.class, args);
		System.out.println("Server started at " + LocalTime.now());
	}

}
