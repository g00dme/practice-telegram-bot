package com.practice.bot;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.time.LocalTime;
import java.util.ArrayList;

public class IssueProcessing {

    public static boolean notificationsEnabled = true;

    public static void issueProcessing(ArrayList<ArrayList<Object>> diff,ArrayList<ArrayList<Object>> old,ArrayList<ArrayList<Object>> close){
        Bot bot = new Bot();

        if(LocalTime.now().isAfter(LocalTime.parse("09:00")) && LocalTime.now().isBefore(LocalTime.parse("18:00")) && notificationsEnabled) {
            for (int i = 0; i < diff.size(); i++) {
                try {
                    if (Bot.users.get(diff.get(i).get(3)) != null) {
                        String text = " Задача была обновлена\n название: %s\n приоритет: %s\n ответственные за исполнение: %s,\n задачу зарегистрировал: %s\n дата последнего коментария: %s";
                        String formatText = String.format(text, diff.get(i).get(0), diff.get(i).get(1), diff.get(i).get(2), diff.get(i).get(4), diff.get(i).get(9));
                        bot.sendMessage(Bot.users.get(diff.get(i).get(3)), formatText);
                    }
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if(LocalTime.now().isAfter(LocalTime.parse("09:00")) && LocalTime.now().isBefore(LocalTime.parse("13:00")) && notificationsEnabled) {
            for (int i = 0; i < old.size(); i++) {
                try {
                    if (Bot.users.get(old.get(i).get(3)) != null) {
                            String text = " У вас есть задача с просроченным комментарием\n название: %s\n дата последнего коментария: %s";
                            String formatText = String.format(text, old.get(i).get(0), old.get(i).get(9));
                            bot.sendMessage(Bot.users.get(old.get(i).get(3)), formatText);
                    }
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if(LocalTime.now().isAfter(LocalTime.parse("09:00")) && LocalTime.now().isBefore(LocalTime.parse("13:00")) && notificationsEnabled) {
            for (int i = 0; i < close.size(); i++) {
                try {
                    if (Bot.users.get(close.get(i).get(3)) != null) {
                        String text = " Задача приближается к крайнему сроку\n название: %s\n приоритет: %s\n ответственные за исполнение: %s,\n задачу зарегистрировал: %s\n дата последнего коментария: %s";
                        String formatText = String.format(text, close.get(i).get(0), close.get(i).get(1), close.get(i).get(2), close.get(i).get(4), close.get(i).get(9));
                        bot.sendMessage(Bot.users.get(close.get(i).get(3)), formatText);
                    }
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
