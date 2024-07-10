package org.example;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Map;

public class IssueProcessing {

//    static Bot bot = new Bot();

    static void getDiffIssue(){
        Map<String, Long> getmap = Bot.getMap();
        ArrayList<ArrayList<Object>> diff = Liist.differences();
        for(int i=0; i<diff.size();i++){
//            try {
//                Bot.sendMessage(getmap.get(diff.get(i).get(3)),"У вас есть обновлённые задачи");
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }

        }


    }
    static void getCloseIssue(ArrayList<ArrayList<Object>> close){
        Map<String, Long> getmap = Bot.getMap();



    }
}
