package com.example.front.command;


import com.example.front.sendmessageservice.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CreateFlowerBotCommand implements BotCommand{

    private final SendBotMessageService sendBotMessageService;

    public CreateFlowerBotCommand(SendBotMessageService sendBotMessageService){

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
        sendBotMessageService.sendMessage(telegramId.toString(), "Please write new flower in format: \n{\n" +
                "\"flowerName\": 1,\n" +
                "\"count\": 2,\n" +
                "\"price\": 3\n" +
                "}");
        return false;
    }
}
