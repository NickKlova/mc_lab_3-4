package com.example.shopservice.service;

import com.example.shopservice.dto.ShopDTO;
import com.example.shopservice.entity.ShopEntity;
import com.example.shopservice.repository.ShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    ShopRepository shopRepository;
    private final Logger logger = LoggerFactory.getLogger("Logger");


    public ShopEntity addShop(ShopDTO shopDTO) {
        ShopEntity shopEntity = new ShopEntity(shopDTO.getName(),
                shopDTO.getCity(), shopDTO.getStreet(), shopDTO.getBuilding());
        logger.info("Created new entity with id {}", shopEntity.getId());
        shopRepository.save(shopEntity);
        logger.info("Saving new entity in db");
        return shopEntity;
    }
    
    public ShopEntity getShopById(int id) {
        logger.info("Try to get shop by id: {}", id);
        ShopEntity shopEntity = shopRepository.findById(id).get();
        logger.info("Got shop with id: {}", id);
        return shopEntity;
    }

}
