package com.example.flowerservice.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
public class FlowerDTO {

    private int id;

    @NotBlank(message = "Name must have not null")
    @Size(max = 255, message = "Max size 255")
    private String flowerName;


    @NotNull
    @Min(value = 0, message = "Count must be > 0")
    private int count;

    @NotNull
    @Min(value = 0, message = "Price must be > 0")
    private int price;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowerDTO that = (FlowerDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
