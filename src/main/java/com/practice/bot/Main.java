package com.practice.bot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    static RedmineBot redmine = new RedmineBot("http://localhost:3000","d8d70e4791fad295441a647234b245234abd7cc2");

    public static void repeat() {
        System.out.println(new java.util.Date());
        redmine.get_difference();
        System.out.println("differences: "+redmine.diff);
        System.out.println("old issues: "+ redmine.diff_old);
        System.out.println("close to deadline "+ redmine.diff_deadlines);
    }
    public static void main(String[] args) {
        ScheduledExecutorService executorService;
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(Main::repeat, 0, 15, TimeUnit.SECONDS);
    }
}