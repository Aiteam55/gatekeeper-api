package com.aiteam.gatekeeper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
public class GatekeeperApplication implements CommandLineRunner {

	@Value("${spring.profiles.active:dev}")
	private String profile;

	public static void main(String[] args) {
		SpringApplication.run(GatekeeperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Active Spring Profile: " + profile);
	}
}
