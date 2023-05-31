package com.example.front.command;


import com.example.front.models.ShopDto;
import com.example.front.sendmessageservice.SendBotMessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AddShopBotCommand implements BotCommand{

    private final SendBotMessageService sendBotMessageService;

    public AddShopBotCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public boolean execute(Update update) {
        String messageContent = update.getMessage().getText();
        ShopDto shopDto;
        try {
            shopDto = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(messageContent, ShopDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String shopId = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI("http://shop-service:8085/shop"))
                    .addParameter("name", shopDto.getName())
                    .addParameter("city", shopDto.getCity())
                    .addParameter("street", shopDto.getStreet())
                    .addParameter("building", String.valueOf(shopDto.getBuilding()))
                    .addHeader("'Content-Type", "application/x-www-form-urlencoded")
                    .build();

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                shopId = EntityUtils.toString(response.getEntity()).split("\"id\":")[1].substring(0,1);
            } finally {
                response.close();
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        sendBotMessageService.sendMessage(String.valueOf(update.getMessage().getChatId()), "Your shop has been added, with id: " + shopId);


        return true;
    }
}
