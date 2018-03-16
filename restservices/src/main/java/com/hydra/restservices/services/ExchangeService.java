package com.hydra.restservices.services;

import com.hydra.restservices.models.cex.io.CurrencyObject;
import com.hydra.restservices.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    public List<CurrencyObject> getAllExchanges(){
        //List<CurrencyObject> exchanges = new ArrayList<>();
        //exchangeRepository.findAll()//.forEach(exchanges::add);
        return exchangeRepository.findAll();//.forEach(exchanges::add);
    }
}
