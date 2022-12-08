package com.sahil.example.baseapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahil.example.baseapp.model.Criteria;
import com.sahil.example.baseapp.model.OrderDto;
import com.sahil.example.baseapp.model.OrderRespDto;
import com.sahil.example.baseapp.model.Product;
import com.sahil.example.baseapp.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ecom")
public class EcomController {
    private static final Logger logger = LoggerFactory.getLogger(EcomController.class);

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Value("${catalog.service.url}")
    String catalogUrl;

    @Value("${order.service.url}")
    String orderUrl;

    @Autowired
    ProductRepository productRepository;

    @PostMapping(value = "/buy")
    public OrderRespDto searchAndBuy(@RequestBody Criteria criteria) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        Object redisValueObject = redisTemplate.opsForValue().get(criteria.getModel());

        List<Product> products = new ArrayList<>();
        if (redisValueObject != null) {
            logger.info(redisValueObject.toString());
            Product product = mapper.readValue(redisValueObject.toString(), Product.class);
            products.add(product);
        }
        else {
            logger.info(catalogUrl);
            logger.info(orderUrl);

            String url = String.format("%s?model=%s", catalogUrl, criteria.getModel());

            logger.info(url);
            ResponseEntity<List<Product>> exchange = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<Product>>() {
                    }
            );
            products.addAll(exchange.getBody());
        }

        Double amount = 0.0;

        if (!products.isEmpty()) {
            Product product = products.get(0);
            redisTemplate.opsForValue().set(product.getModel(), mapper.writeValueAsString(product));
        }
        for (Product product: products){
            amount += product.getPrice();
        }

        OrderDto orderDto = new OrderDto();
        orderDto.setProducts(products);
        orderDto.setAmount(amount);

        logger.info(orderUrl);
        HttpEntity<String> request =
                new HttpEntity<String>(mapper.writeValueAsString(orderDto), headers);

        ResponseEntity<OrderRespDto> responseEntityStr = restTemplate.
                postForEntity(orderUrl, request, OrderRespDto.class);

        return responseEntityStr.getBody();
    }
}
