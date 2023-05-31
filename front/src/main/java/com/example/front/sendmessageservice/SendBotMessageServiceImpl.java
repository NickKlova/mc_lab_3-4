package com.example.front.sendmessageservice;

import com.example.front.telegrambot.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService{
    public static final int PAGE_SIZE = 5;

    private final Logger logger = LoggerFactory.getLogger(SendBotMessageServiceImpl.class);

    private final TelegramBot telegramBot;

    @Autowired
    public SendBotMessageServiceImpl(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);
        try {
            telegramBot.execute(sendMessage);
            logger.info("send message {}", message);
        } catch (TelegramApiException e) {
            logger.warn(e.getLocalizedMessage());
        }
    }

    @Override
    public void sendMessage(SendMessage message) {
        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            logger.warn(e.getLocalizedMessage());
        }
    }


    @Override
    public void setReplyMarkup(String chatId, ReplyKeyboardMarkup replyKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText("Menu");
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.warn(e.getLocalizedMessage());
        }
    }

    @Override
    public void sendMenu(SetMyCommands command) {
        try {
            telegramBot.execute(command);
        } catch (TelegramApiException e) {
            logger.warn(e.getLocalizedMessage());
        }
    }

}
