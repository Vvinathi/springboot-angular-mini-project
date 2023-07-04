package com.bl.cm.customermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = { "com.bl.cm.model", "com.bl.cm.controllers", "com.bl.cm.exception", "com.bl.cm.repo",
		"com.bl.cm.service", })
@EntityScan(basePackages = { "com.bl.cm.model" })
@EnableJpaRepositories(basePackages = { "com.bl.cm.repo" })
@SpringBootApplication

public class CustomermanagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomermanagementApplication.class, args);
	}

}
