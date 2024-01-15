package com.sistemapacto.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;

@SpringBootApplication
public class SistemapactoServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(SistemapactoServerApplication.class, args);
		System.out.println("Server started at " + LocalTime.now());
	}

}
