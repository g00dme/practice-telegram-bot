package org.example;

import java.util.ArrayList;

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
        singlList.add(9);
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
            singleList.add(9+i);

            diff.add(singleList);
        }

        return diff;
    }

    static ArrayList<ArrayList<Object>> oldIssues (){
        ArrayList<ArrayList<Object>> old = new ArrayList<ArrayList<Object>>();
        for (int i=0; i<3; i++) {
            ArrayList<Object> singleList = new ArrayList<Object>();
            singleList.add(1+i);
            singleList.add(2+i);
            singleList.add(3+i);
            singleList.add("anton"+i+"@mail.ru");
            singleList.add(5+i);
            singleList.add(6+i);
            singleList.add(7+i);
            singleList.add(8+i);
            singleList.add(9+i);

            old.add(singleList);
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
        singleList.add(9);

        old.add(singleList);

        for (int i=0; i<2; i++) {
            ArrayList<Object> singlList = new ArrayList<Object>();
            singlList.add(1+i);
            singlList.add(2+i);
            singlList.add(3+i);
            singlList.add("anton"+i+"@mail.ru");
            singlList.add(5+i);
            singlList.add(6+i);
            singlList.add(7+i);
            singlList.add(8+i);
            singlList.add(9+i);

            old.add(singlList);
        }

        return old;
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
            singleList.add(9+i);

            close.add(singleList);
        }
        ArrayList<Object> singleList = new ArrayList<Object>();
        singleList.add(1);
        singleList.add(2);
        singleList.add(3);
        singleList.add("funny@mail.ru");
        singleList.add(5);
        singleList.add(6);
        singleList.add(7);
        singleList.add(8);
        singleList.add(9);

        close.add(singleList);

        return close;
    }

}
