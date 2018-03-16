package com.hydra.restservices.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/exchange")
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object getAllExchanges() {
        return null;
    }

    @RequestMapping(value = "/{exchangeId}", method = RequestMethod.GET)
    public Object getCoinsInAnExchange(@PathVariable String exchangeId) {
        return null;
    }


    @RequestMapping(value = "/{exchangeId}/{coinId}", method = RequestMethod.GET)
    public Object getCoinPriceInAnExchange(@PathVariable String exchangeId,@PathVariable String coinId){
        return null;
    }


}
