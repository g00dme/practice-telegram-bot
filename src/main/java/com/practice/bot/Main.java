package com.practice.bot;


import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.taskadapter.redmineapi.RedmineException;


import static com.practice.bot.IssueProcessing.issueProcessing;

public class Main {
    static RedmineBot redmine = new RedmineBot("http://redmine:3000","https://hosting228","d8d70e4791fad295441a647234b245234abd7cc2");
    static final long CHAT_ID_ADMIN = 5355357934L;
    static final String EMAIL_ADMIN = "leonidchr@mail.ru";

    public static void repeat() {
        System.out.println(new Date());
        redmine.get_difference();
        System.out.println(redmine.links);
        System.out.println("differences: "+redmine.diff);
        System.out.println("old issues: "+ redmine.diff_old);
        System.out.println("close to deadline "+ redmine.diff_deadlines);
        issueProcessing(redmine.diff,redmine.diff_old,redmine.diff_deadlines);
    }
    public static void main(String[] args) {
        Bot.users.put(EMAIL_ADMIN, CHAT_ID_ADMIN);
        ScheduledExecutorService executorService;
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(Main::repeat, 0, 5, TimeUnit.MINUTES);
    }
}