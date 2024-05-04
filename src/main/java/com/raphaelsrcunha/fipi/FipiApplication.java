package com.raphaelsrcunha.fipi;

import com.raphaelsrcunha.fipi.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.showMenu();

	}
}
