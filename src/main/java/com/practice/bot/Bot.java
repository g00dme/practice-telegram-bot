package com.practice.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.HashMap;
import java.util.Map;

import static com.practice.bot.Main.CHAT_ID_ADMIN;
import static com.practice.bot.RedmineBot.now_all;


public class Bot extends TelegramLongPollingBot {
    static Map<String, Long> users = new HashMap<>();
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

        if(update.hasMessage() && update.getMessage().hasText() ) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText){
                case "/start":
                    if (!users.containsValue(chatId)) {
                        startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                        break;
                    } else {
                        try {
                            sendMessage(chatId, "Вы уже авторизованы");
                            break;
                        } catch (TelegramApiException e) {
                            LOG.error("Не удалось отправить сообщение пользователю");
                        }
                    }
                case "/enable":
                    if (chatId == CHAT_ID_ADMIN) {
                        try {
                            IssueProcessing.notificationsEnabled = true;
                            sendMessage(chatId, "Уведомления включены");
                            break;
                        } catch (TelegramApiException e) {
                            LOG.error("Не удалось отправить сообщение пользователю");
                        }
                    }
                case "/disable":
                    if(chatId == CHAT_ID_ADMIN){
                        try {
                            IssueProcessing.notificationsEnabled = false;
                            sendMessage(chatId,"Уведомления отключены");
                            break;
                        } catch (TelegramApiException e) {
                            LOG.error("Не удалось отправить сообщение пользователю");
                        }
                    }
                default:{
                    if (messageText.startsWith("Моя почта")){
                        if (users.get(messageText.substring(10).trim())!=null){
                            try {
                                sendMessage(chatId,"Извините, пользователь с такой почтой уже зарегистрирован");
                            } catch (TelegramApiException e) {
                                LOG.error("Не удалось отправить сообщение пользователю");
                            }
                        }else {
                            users.put(messageText.substring(10).trim(),chatId); //todo добавить проверку правильности написания почты
                            try {
                                sendMessage(chatId,"Регистрация прошла успешно, ожидайте уведомлений");
                            } catch (TelegramApiException e) {
                                LOG.error("Не удалось отправить сообщение пользователю");
                            }
                        }
                    }
                    if(messageText.startsWith("Проект")){
                        String projectName = messageText.substring(7).trim();
                        if (RedmineBot.links.containsKey(projectName)){
                            try {
                                sendMessage(chatId,"Ссылка на проект " + projectName + " " + RedmineBot.links.get(projectName));
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        } else{
                            try {
                                sendMessage(chatId,"Проект с таким названием не найден");
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    if (chatId == CHAT_ID_ADMIN && messageText.startsWith("Задачи пользователя")){
                        String userEmail = messageText.substring(20).trim();

                        for (int i=0; i < now_all.size(); i++){
                            try {
                                String allIssueUser = (String) now_all.get(i).get(3);
                                if (allIssueUser.equals(userEmail)) {
                                    String text = " Название задачи: %s\n дата создания: $s\n приоритет: %s\n ответственные за исполнение: %s,\n задачу зарегистрировал: %s\n дата последнего коментария: %s";
                                    String formatText = String.format(text, now_all.get(i).get(0),now_all.get(i).get(12), now_all.get(i).get(1), now_all.get(i).get(2), now_all.get(i).get(4), now_all.get(i).get(9));
                                    sendMessage(chatId, formatText);
                                }
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }
    }

    private void startCommandReceived(long chatId, String name){

        String answer = "Здравствуй, " + name + ",\nчтобы начать получать уведомления о задачах в Redmine, пройди регистрацию, отправив сообщение обязательного вида: Моя почта FunnyMonkey@gmail.com";
        try {
            sendMessage(chatId, answer);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка при формировании ответа на команду /start");
        }

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

}
