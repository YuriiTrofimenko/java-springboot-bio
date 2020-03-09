package org.tyaa.springboot.hw.bio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BioApplication {

	public static void main(String[] args) {
            SpringApplication.run(BioApplication.class, args);
	}
}
