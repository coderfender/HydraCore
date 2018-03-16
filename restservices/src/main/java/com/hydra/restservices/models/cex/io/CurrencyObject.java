package com.hydra.restservices.models.cex.io;

import org.bson.Document;

public class CurrencyObject {
    String exchangeName;
    String type;
    String price;
    String curr;

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

    public Document getDocument() {
        return new Document().append("exchangeName", exchangeName).append("type", type).append("price", price).
                append("curr", curr);
    }

    @Override
    public String toString() {
        return "CurrencyObject{" +
                "exchangeName='" + exchangeName + '\'' +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", curr='" + curr + '\'' +
                '}';
    }
}
