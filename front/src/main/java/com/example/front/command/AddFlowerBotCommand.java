package com.example.front.command;

import com.example.front.models.FlowerDto;
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

public class AddFlowerBotCommand implements BotCommand{
    private final SendBotMessageService sendBotMessageService;

    public AddFlowerBotCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public boolean execute(Update update) {
        String messageContent = update.getMessage().getText();
        FlowerDto flowerDto;
        try {
            flowerDto = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(messageContent, FlowerDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String flowerId = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI("http://flower-service:8085/flower"))
                    .addParameter("flowerName", String.valueOf(flowerDto.getFlowerName()))
                    .addParameter("count", String.valueOf(flowerDto.getCount()))
                    .addParameter("price", String.valueOf(flowerDto.getPrice()))
                    .addHeader("'Content-Type", "application/x-www-form-urlencoded")
                    .build();

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                flowerId = EntityUtils.toString(response.getEntity()).split("\"id\":")[1].substring(0,1);
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

        sendBotMessageService.sendMessage(String.valueOf(update.getMessage().getChatId()), "Your flower has been added, with id: " + flowerId);


        return true;
    }
}
