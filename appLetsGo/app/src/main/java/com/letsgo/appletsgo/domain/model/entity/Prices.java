package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;

/**
 * Created by louislopez on 6/03/17.
 */

public class Prices implements Serializable {
        private String type_entries;
        private String price;

    public String getType() {
        return type_entries;
    }

    public void setType(String type) {
        this.type_entries = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Prices{" +
                "type='" + type_entries + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
