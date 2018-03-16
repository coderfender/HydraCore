package com.hydra.restservices.controllers;

import com.hydra.restservices.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hydra.restservices.services.ExchangeService;

@RestController
@RequestMapping(value = "/exchange")
public class MainController {

    @Autowired
    private ExchangeService exchangeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object getAllExchanges() {
        System.out.println(" Someone hit the API");
        System.out.println(exchangeService.getAllExchanges());
        return exchangeService.getAllExchanges();
    }

    @RequestMapping(value = "/{exchangeId}", method = RequestMethod.GET)
    public Object getCoinsInExchange(@PathVariable String exchangeId) {
        System.out.println(" Someone hit the API");
        return "Get Coins In Exchange";
    }

    @RequestMapping(value = "/{exchangeId}/{coinId}", method = RequestMethod.GET)
    public Object getCoinPriceInExchange(@PathVariable String exchangeId,@PathVariable String coinId) {
        System.out.println(" Someone hit the API");
        return "Return Prices";
    }

}