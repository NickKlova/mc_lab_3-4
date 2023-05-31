package com.example.front.command;

import com.example.front.sendmessageservice.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CreateShopBotCommand implements BotCommand{
    private final SendBotMessageService sendBotMessageService;

    public CreateShopBotCommand(SendBotMessageService sendBotMessageService){

        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public boolean execute(Update update) {
        Long telegramId;
        if (update.hasCallbackQuery()){
            telegramId = update.getCallbackQuery().getFrom().getId();
        } else {
            telegramId = update.getMessage().getChatId();
        }
        sendBotMessageService.sendMessage(telegramId.toString(), "Please write new shop in format: \n{\n" +
                "\"name\": \"example\",\n" +
                "\"city\": \"example\",\n" +
                "\"street\": \"example\",\n" +
                "\"building\": 1 \n}");
        return false;
    }
}
