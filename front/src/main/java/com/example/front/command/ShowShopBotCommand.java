package com.example.front.command;


import com.example.front.http.HttpSender;
import com.example.front.http.HttpSenderI;
import com.example.front.sendmessageservice.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ShowShopBotCommand implements BotCommand{
    private final HttpSender httpSender = new HttpSenderI();
    private final SendBotMessageService sendBotMessageService;
    public ShowShopBotCommand(SendBotMessageService sendBotMessageService){
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public boolean execute(Update update) {
        //System.out.println(update.getMessage().getText());

        String response = httpSender.sendGetRequest("http://shop-service:8085/shop/" + update.getMessage().getText());
        sendBotMessageService.sendMessage(String.valueOf(update.getMessage().getChatId()), response);
        return false;
    }
}
