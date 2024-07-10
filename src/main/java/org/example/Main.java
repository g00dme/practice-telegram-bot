package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {

//    static Liist liist = new Liist();
//    static Bot bot = new Bot();
//    static IssueProcessing issueProcessing = new IssueProcessing();

    public static void repeat(){
        System.out.println(new java.util.Date());
        System.out.println(Bot.getMap());
        System.out.println("Обновлённые задачи задачи\n"+Liist.differences());
        System.out.println("Сгорающие задачи\n"+Liist.closeToDeadline());
        IssueProcessing.getDiffIssue();

    }

    public static void main(String[] args) {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        ScheduledExecutorService executorService;
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(Main::repeat, 0, 1, TimeUnit.MINUTES);
    }
}