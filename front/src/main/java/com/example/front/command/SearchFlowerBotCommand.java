package com.example.front.command;

import com.example.front.sendmessageservice.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SearchFlowerBotCommand implements BotCommand{

    private final SendBotMessageService sendBotMessageService;

    public SearchFlowerBotCommand(SendBotMessageService sendBotMessageService){
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public boolean execute(Update update) {
        Long telegramId;
        if(update.hasCallbackQuery()){
            telegramId = update.getCallbackQuery().getFrom().getId();
        }else {
            telegramId = update.getMessage().getChatId();
        }
        sendBotMessageService.sendMessage(String.valueOf(telegramId), "Type id of flower");
        return false;
    }
}
