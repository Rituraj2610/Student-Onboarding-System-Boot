package com.rituraj.candidateOnboardingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class CandidateOnboardingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CandidateOnboardingSystemApplication.class, args);
	}

}
