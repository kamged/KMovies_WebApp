package com.kamged.kmovies;

import com.kamged.kmovies.services.OMDbService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.kamged.kmovies")
public class KMoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(KMoviesApplication.class, args);

	}


}
