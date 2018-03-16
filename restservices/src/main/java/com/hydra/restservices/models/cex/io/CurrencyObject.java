package com.hydra.restservices.models.cex.io;

import org.bson.Document;
import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document(collection = "exchange")
public class CurrencyObject {
/*
    @Id
    String id;
*/
    String exchangeName;
    String type;
    String price;
    String curr;
    String time;

/*
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
*/

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
