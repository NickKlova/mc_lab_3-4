package com.example.front.models;


public enum PositionMenu {
    MENU_START,
    MENU_CREATE_FLOWER("/add_flower"),
    MENU_SEARCH_FLOWER("/show_flower"),
    MENU_CREATE_SHOP("/add_shop"),
    MENU_SEARCH_SHOP("/show_shop"),

    ;

    private String nextCommand;

    PositionMenu(String nextCommand) {
        this.nextCommand = nextCommand;
    }

    PositionMenu() {
    }

    public String getNextCommand() {
        return this.nextCommand;
    }
}
