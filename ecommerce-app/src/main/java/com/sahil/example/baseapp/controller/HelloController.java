package com.sahil.example.baseapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {


    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(value = "/health")
    public String healthCheck() {

        logger.info("HealthCheckAPI");
        return "Ok";
    }
}
