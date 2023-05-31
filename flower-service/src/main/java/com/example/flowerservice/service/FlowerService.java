package com.example.flowerservice.service;


import com.example.flowerservice.dto.FlowerDTO;
import com.example.flowerservice.entity.FlowerEntity;
import com.example.flowerservice.repository.FlowerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowerService {

    @Autowired
    FlowerRepository flowerRepository;
    private final Logger logger = LoggerFactory.getLogger("Logger");


    public FlowerEntity addFlower(FlowerDTO flowerDTO) {
        FlowerEntity flowerEntity = new FlowerEntity(flowerDTO.getFlowerName(),
                flowerDTO.getCount(), flowerDTO.getPrice());
        logger.info("Created new entity with id {}", flowerEntity.getId());
        flowerRepository.save(flowerEntity);
        logger.info("Saving new entity in db");
        return flowerEntity;
    }
    
    public FlowerEntity getFlowerById(int id) {
        logger.info("Try to get flower by id: {}", id);
        FlowerEntity flowerEntity = flowerRepository.findById(id).get();
        logger.info("Got flower with id: {}", id);
        return flowerEntity;
    }

}
