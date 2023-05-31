package com.example.shopservice.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class ShopDTO {

    private int id;
    private String name;
    private String city;
    private String street;
    private int building;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopDTO that = (ShopDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
