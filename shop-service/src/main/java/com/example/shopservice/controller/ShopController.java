package com.example.shopservice.controller;


import com.example.shopservice.dto.FlowerDTO;
import com.example.shopservice.dto.ShopDTO;
import com.example.shopservice.entity.ShopEntity;
import com.example.shopservice.service.ShopService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@RestController
public class ShopController {

    private final Logger logger = LoggerFactory.getLogger("Logger");
    private int counter=1;



    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Autowired
    ShopService shopService;

    @PostMapping("/shop")
    public ResponseEntity<ShopEntity> addShop (@RequestBody ShopDTO shopDTO) {
        logger.info("Received POST request on creating new shop");
        ShopEntity shopEntity = shopService.addShop(shopDTO);
        logger.info("New shop saved");
        return ResponseEntity.ok(shopEntity);
    }


    @GetMapping("/shop/{id}")
    public ResponseEntity<ShopEntity> getShopById (@PathVariable int id) {
        logger.info("Received GET request on getting shop with id: {}", id);
        ShopEntity shopEntity = shopService.getShopById(id);
        logger.info("Returned shop with id: {}", id);
        return ResponseEntity.ok(shopEntity);
    }


    @GetMapping("/shop/flower")
    @CircuitBreaker(name ="shop",fallbackMethod = "fallBack")
    @Retry(name = "shop",fallbackMethod = "getFlowers")
    public ResponseEntity<FlowerDTO> communication () {

        return ResponseEntity.ok(restTemplate.getForObject("http://flower-service:8085/flower/1", FlowerDTO.class));
    }

    public ResponseEntity getFlowers (Exception e)
    {
        System.out.println("Retry method called "+counter++);
        return ResponseEntity.ok(restTemplate.getForObject("http://flower-service:8085/flower/1", FlowerDTO.class));
    }




    @PostMapping(value = "/shop", consumes = "application/x-www-form-urlencoded")
    public  ResponseEntity<ShopEntity> addShopByTelegram (ShopDTO shopDTO) {
        logger.info("Received POST request on creating new shop");
        ShopEntity shopEntity = shopService.addShop(shopDTO);
        logger.info("New shop saved");
        return ResponseEntity.ok(shopEntity);
    }

    public ResponseEntity fallBack (Exception e){
        return ResponseEntity.ok("Shop - service fell");
    }


}
