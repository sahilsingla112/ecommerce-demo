package com.sahil.example.baseapp.model;

import java.util.List;

public class OrderRespDto {
    String id;
    List<Product> products;
    Double amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderRespDto{" +
                "id='" + id + '\'' +
                ", products=" + products +
                ", amount=" + amount +
                '}';
    }
}
