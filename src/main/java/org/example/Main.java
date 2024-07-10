package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.example.IssueProcessing.issueProcessing;


public class Main {
    static final long CHAT_ID_ADMIN = 5355357934L;


    public static void repeat(){
        Bot bot = new Bot();
        System.out.println(new java.util.Date());
        System.out.println(Bot.map);
        System.out.println("Обновлённые задачи задачи\n"+Liist.differences());
        System.out.println("Задачи с просроченным комментарием\n"+Liist.oldIssues());
        System.out.println("Сгорающие задачи\n"+Liist.closeToDeadline());

        try {
            bot.sendMessage(CHAT_ID_ADMIN,"проверка");
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        issueProcessing(Liist.differences(),Liist.oldIssues(),Liist.closeToDeadline());
    }

    static class AliveTask extends TimerTask {
        public void run() {
            repeat(); // Вызываем метод из таймера
        }
    }


    public static void main(String[] args) {

        try {
            Bot.map.put("leonidchr@mail.ru", CHAT_ID_ADMIN);
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

//        ScheduledExecutorService executorService;
//        executorService = Executors.newSingleThreadScheduledExecutor();
//        executorService.scheduleAtFixedRate(Main::repeat, 0, 1, TimeUnit.MINUTES);

        Timer timer = new Timer();
        timer.schedule(new AliveTask(), 0, 60000); //в миллисекундах
    }
}