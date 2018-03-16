package com.hydra.restservices;

import com.hydra.restservices.services.HydraConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.hydra.restservices.controllers", "com.hydra.restservices.services"})
public class RestservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestservicesApplication.class, args);
		//new HydraConsumer();
	}
}
