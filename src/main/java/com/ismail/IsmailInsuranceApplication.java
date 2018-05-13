package com.ismail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IsmailInsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsmailInsuranceApplication.class, args);
	}
}
