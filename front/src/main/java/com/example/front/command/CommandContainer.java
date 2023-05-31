package com.example.front.command;

import com.example.front.sendmessageservice.SendBotMessageService;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandContainer {

    private final ImmutableMap<String, BotCommand> commandMap;


    @Autowired
    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandMap = ImmutableMap.<String, BotCommand>builder()
                .put("/start", new MainMenuBotCommand(sendBotMessageService))
                .put("/flower", new FlowerMenuBotCommand(sendBotMessageService))
                .put("/create_flower", new CreateFlowerBotCommand(sendBotMessageService))
                .put("/add_flower", new AddFlowerBotCommand(sendBotMessageService))
                .put("/search_flower", new SearchFlowerBotCommand(sendBotMessageService))
                .put("/show_flower", new ShowFlowerBotCommand(sendBotMessageService))
                .put("/create_shop", new CreateShopBotCommand(sendBotMessageService))
                .put("/add_shop", new AddShopBotCommand(sendBotMessageService))
                .put("/shop", new ShopMenuBotCommand(sendBotMessageService))
                .put("/search_shop", new SearchShopBotCommand(sendBotMessageService))
                .put("/show_shop", new ShowShopBotCommand(sendBotMessageService))
                .build();

    }

    public BotCommand retrieveCommand(String commandIdentifier) {
        return commandMap.get(commandIdentifier);
    }
}