package com.sahil.example.baseapp.model;

import java.util.List;

public class OrderDto {
    List<Product> products;
    Double amount;

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
}
