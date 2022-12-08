package com.sahil.example.baseapp.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahil.example.baseapp.model.OrderDto;
import com.sahil.example.baseapp.model.Orderinfo;
import com.sahil.example.baseapp.repository.OrderinfoRepository;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    OrderinfoRepository orderinfoRepository;

    @PostMapping(value = "/checkout")
    public Orderinfo placeOrder(@RequestBody OrderDto order) throws JsonProcessingException {
        Orderinfo orderinfo = new Orderinfo();
        orderinfo.setAmount(order.getAmount());
        orderinfo.setProducts(order.getProducts());

        Orderinfo save = orderinfoRepository.save(orderinfo);
        return save;
    }
}
