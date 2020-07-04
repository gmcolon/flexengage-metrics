package com.flexengage.metrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class MetricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricsApplication.class, args);
	}
}
