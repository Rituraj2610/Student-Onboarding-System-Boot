package com.rituraj.candidateOnboardingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CandidateOnboardingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CandidateOnboardingSystemApplication.class, args);
	}

}
