package com.sahil.example.baseapp.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Product")
public class Product implements Serializable {
    String id;
    String model;
    Double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
