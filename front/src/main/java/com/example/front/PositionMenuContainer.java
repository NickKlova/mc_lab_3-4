package com.example.front;

import com.example.front.cache.BotUserCache;
import com.example.front.command.CommandContainer;
import com.example.front.models.PositionMenu;
import com.example.front.models.UserDto;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class PositionMenuContainer {

    CommandContainer commandContainer;

    private final ImmutableMap<String, PositionMenu> positionMenuImmutableMap;

    @Autowired
    public PositionMenuContainer (CommandContainer commandContainer) {
        positionMenuImmutableMap = ImmutableMap.<String, PositionMenu>builder()
                .put("/create_flower", PositionMenu.MENU_CREATE_FLOWER)
                .put("/search_flower", PositionMenu.MENU_SEARCH_FLOWER)
                .put("/create_shop", PositionMenu.MENU_CREATE_SHOP)
                .put("/search_shop", PositionMenu.MENU_SEARCH_SHOP)
                .build();
        this.commandContainer = commandContainer;
    }

    public void executeCommand (Update update) {
        String command;
        if(update.hasCallbackQuery()){
            command = update.getCallbackQuery().getData();
        } else {
            command = update.getMessage().getText();
        }
        UserDto userDto = new UserDto();
        userDto.setId_telegram(update.getCallbackQuery().getFrom().getId());
        userDto.setName(update.getCallbackQuery().getFrom().getUserName());
        userDto.setPositionMenu(positionMenuImmutableMap.get(command));
        BotUserCache.addUserDto(userDto);
        commandContainer.retrieveCommand(command).execute(update);
    }
}
