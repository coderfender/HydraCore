package com.hydra.restservices.repository;

import com.hydra.restservices.models.cex.io.CurrencyObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExchangeRepository extends MongoRepository<CurrencyObject,String> {
}
