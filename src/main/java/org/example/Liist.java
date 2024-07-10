package org.example;

import java.util.ArrayList;


public class Liist {
    static ArrayList<ArrayList<Object>> differences (){
        ArrayList<ArrayList<Object>> diff = new ArrayList<ArrayList<Object>>();
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
        ArrayList<Object> singleList = new ArrayList<Object>();
        singleList.add(1);
        singleList.add(2);
        singleList.add(3);
        singleList.add("leonidchr@mail.ru");
        singleList.add(5);
        singleList.add(6);
        singleList.add(7);
        singleList.add(8);

        diff.add(singleList);

        return diff;
    }
}
