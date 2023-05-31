package com.example.front.models;

import lombok.Data;

@Data
public class UserDto {
    private Long id_telegram;
    private String name;

    private PositionMenu positionMenu;


}
