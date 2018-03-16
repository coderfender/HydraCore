package com.hydra.restservices.config;

import com.hydra.restservices.repository.ExchangeRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = ExchangeRepository.class)
@Configuration
public class MongoDBConfig {

}
