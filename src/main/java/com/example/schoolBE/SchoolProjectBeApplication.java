package com.example.schoolBE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.schoolBE.repositories")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SchoolProjectBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolProjectBeApplication.class, args);
	}

}
