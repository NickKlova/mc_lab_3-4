package com.example.flowerservice.controller;

import com.example.flowerservice.dto.FlowerDTO;
import com.example.flowerservice.entity.FlowerEntity;
import com.example.flowerservice.service.FlowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class FlowerController {

    private final Logger logger = LoggerFactory.getLogger("Logger");

    @Autowired
    FlowerService flowerService;

    @PostMapping("/flower")
    public ResponseEntity<FlowerEntity> addFlower (@RequestBody FlowerDTO flowerDTO) {
        logger.info("Received POST request on creating new flower");
        FlowerEntity flowerEntity = flowerService.addFlower(flowerDTO);
        logger.info("New flower saved");
        return ResponseEntity.ok(flowerEntity);
    }


    @GetMapping("/flower/{id}")
    public ResponseEntity<FlowerEntity> getFlowerById (@PathVariable int id) {
        logger.info("Received GET request on getting flower with id: {}", id);
        FlowerEntity flowerEntity = flowerService.getFlowerById(id);
        logger.info("Returned flower with id: {}", id);
        return ResponseEntity.ok(flowerEntity);
    }

    @PostMapping(value = "/flower", consumes = "application/x-www-form-urlencoded")
    public  ResponseEntity<FlowerEntity> addScheduleByTelegram (FlowerDTO flowerDTO) {
        logger.info("Received POST request on creating new flower");
        FlowerEntity flowerEntity = flowerService.addFlower(flowerDTO);
        logger.info("New flower saved");
        return ResponseEntity.ok(flowerEntity);
    }

}
