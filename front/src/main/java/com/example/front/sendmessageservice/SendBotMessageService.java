package com.example.front.sendmessageservice;

import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public interface SendBotMessageService {
    void sendMessage(String chatId, String message);

    void sendMessage(SendMessage message);


    void setReplyMarkup(String chatId, ReplyKeyboardMarkup replyKeyboardMarkup);

    void sendMenu(SetMyCommands command);

}
