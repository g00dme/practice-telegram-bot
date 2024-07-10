package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Map;


public class Liist {


    static ArrayList<ArrayList<Object>> differences (){
        ArrayList<ArrayList<Object>> diff = new ArrayList<ArrayList<Object>>();
        ArrayList<Object> singlList = new ArrayList<Object>();
        singlList.add(10);
        singlList.add(2);
        singlList.add(30);
        singlList.add("leonidchr@mail.ru");
        singlList.add(5);
        singlList.add(60);
        singlList.add(7);
        singlList.add(80);
        diff.add(singlList);

        for (int i=0; i<5; i++) {
            ArrayList<Object> singleList = new ArrayList<Object>();
            singleList.add(1+i);
            singleList.add(2+i);
            singleList.add(3+i);
            singleList.add("anton"+i+"@mail.ru");
            singleList.add(5+i);
            singleList.add(6+i);
            singleList.add(7+i);
            singleList.add(8+i);

            diff.add(singleList);
        }

        return diff;
    }

    static ArrayList<ArrayList<Object>> closeToDeadline (){
        ArrayList<ArrayList<Object>> close = new ArrayList<ArrayList<Object>>();
        for (int i=0; i<5; i++) {
            ArrayList<Object> singleList = new ArrayList<Object>();
            singleList.add(1+i);
            singleList.add(2+i);
            singleList.add(3+i);
            singleList.add("anton"+i+"@mail.ru");
            singleList.add(5+i);
            singleList.add(6+i);
            singleList.add(7+i);
            singleList.add(8+i);

            close.add(singleList);
        }
        ArrayList<Object> singleList = new ArrayList<Object>();
        singleList.add(1);
        singleList.add(2);
        singleList.add(3);
        singleList.add("leonidchr@mail.ru");
        singleList.add(5);
        singleList.add(6);
        singleList.add(7);
        singleList.add(8);

        close.add(singleList);

        return close;
    }

}
