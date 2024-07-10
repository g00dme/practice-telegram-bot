package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Bot extends TelegramLongPollingBot {
    static Map<String, Long> map = new HashMap<>();
    long chatIdAdmin = 5355357934L;

    private static final Logger LOG = LoggerFactory.getLogger(Bot.class);

    @Override
    public String getBotUsername() {
        return "RedmineMessage_bot";
    }

    @Override
    public String getBotToken() {
        return "7390257771:AAGIunT84oocSNufKgUF5zuMBYGGte-PkL0";
    }

    @Override
    public void onUpdateReceived(Update update) {
        map.put("leonidchr@mail.ru", chatIdAdmin);

        if(update.hasMessage() && update.getMessage().hasText() ) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText){
                case "/start":{
                    try {
                        startCommandReceived(chatId,update.getMessage().getChat().getFirstName());
                    } catch (TelegramApiException e) {
                        LOG.error("Ошибка при попытке ответить пользователю на комаду /start");
                    }
                }
                case "/help":{

                }
                default:{
                    if (update.getMessage().getText().startsWith("Моя почта")){
                        if (map.get(update.getMessage().getText().substring(10).trim())!=null){
                            String text = "Извините, пользователь с такой почтой уже зарегистрирован или авторизован";
                            try {
                                sendMessage(chatId,text);
                            } catch (TelegramApiException e) {
                                LOG.error("Не удалось отправить сообщение пользователю");
                            }
                        }else {
                            map.put(update.getMessage().getText().substring(10).trim(),update.getMessage().getChatId());
                            String text = "Почта успешно получена, ожидайте уведомлений";
                            try {
                                sendMessage(update.getMessage().getChatId(),text);
                            } catch (TelegramApiException e) {
                                LOG.error("Не удалось отправить сообщение пользователю");
                            }
                            System.out.println(map);
                        }

                    }else {
                        LOG.error("Чё написал?");
                    }
                }
            }
        }
    }

    private void startCommandReceived(long chatId, String name) throws TelegramApiException{

        String Answer = "Здравствуй, " + name + ",\nчтобы начать получать уведомления о задачах в Redmine, пройди регистрацию или авторизируйся, отправив сообщение обязательного вида: Моя почта FunnyMonkey@gmail.com";
        sendMessage(chatId, Answer);

        LOG.info("Ответ на команду /start");
    }
    public void sendMessage(long chatId, String textToSend) throws TelegramApiException{
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try{
            execute(message);
        } catch (TelegramApiException e) {
            LOG.error("Не получилось сформировать ответ пользователю");
        }

    }
    static Map<String, Long> getMap(){
        Map<String, Long> botmap = new HashMap<>();
        botmap.putAll(map);
        return botmap;
    }

}
