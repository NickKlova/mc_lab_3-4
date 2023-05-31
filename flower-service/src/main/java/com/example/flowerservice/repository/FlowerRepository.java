package com.example.flowerservice.repository;


import com.example.flowerservice.entity.FlowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepository extends JpaRepository<FlowerEntity, Integer> {
}


