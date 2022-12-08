package com.sahil.example.baseapp.controller;

import com.sahil.example.baseapp.model.Product;
import com.sahil.example.baseapp.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "/products")
    public List<Product> getProducts(@RequestParam String model){

        logger.info("Catalog service to fetch products with model: " + model);
        List<Product> products = productRepository.findByModel(model);
        return products;
    }
}
