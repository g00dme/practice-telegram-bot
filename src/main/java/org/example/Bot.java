package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

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
    public void onUpdateReceived(Update update) {         //обрабатывает пользовательские команды

        if(update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText){
                case "/start":
                    try {
                        startCommandReceived(chatId,update.getMessage().getChat().getFirstName());
                    } catch (TelegramApiException e) {
                        //LOG.error("Ошибка ответа пользователю на комаду /start");     //после добавления throws в BotInitialaizer ^ здесь их можно убрать
                    }
                    break;
                default:
                    try {
                        sendMessage(chatId, "Sorry");
                    } catch (TelegramApiException e) {
                        //LOG.error("Ошибка при ответе на сообщение пользователя");
                    }

            }
        }
    }

    private void startCommandReceived(long chatId, String name) throws TelegramApiException{

        String Answer = "Hi, " + name + ", you'a nice person ";
        sendMessage(chatId, Answer);

        LOG.info("Ответ на команду /start"); //можно убрать
    }
    private void sendMessage(long chatId, String textToSend) throws TelegramApiException{
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try{
            execute(message);
        } catch (TelegramApiException e) {
            LOG.error("Не получилось сформировать ответ пользователю");
        }

    }
}
