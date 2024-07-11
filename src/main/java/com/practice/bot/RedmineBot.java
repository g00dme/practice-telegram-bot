package com.practice.bot;

import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Journal;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedmineBot {
    RedmineManager mgr;
    String url;
    String api;
    ArrayList<ArrayList<Object>> old_all;
    ArrayList<ArrayList<Object>> now_all;
    ArrayList<ArrayList<Object>> diff;

    ArrayList<ArrayList<Object>> old_issue=new ArrayList<>();
    ArrayList<ArrayList<Object>> new_old_issue;
    ArrayList<ArrayList<Object>> diff_old;

    ArrayList<ArrayList<Object>> dealines=new ArrayList<>();
    ArrayList<ArrayList<Object>> new_dealines;
    ArrayList<ArrayList<Object>> diff_deadlines;




    RedmineBot(String url, String api) {
        this.url=url;
        this.api=api;

        this.mgr=create_connection();
        this.old_all=get_all_issue();
    }

    RedmineManager create_connection() {
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(url, api);
        mgr.setObjectsPerPage(100);
        return mgr;
    }

    Date get_note_date(Issue issue) {
        try {
            Collection<Journal> journals = this.mgr.getIssueManager().getIssueById(issue.getId(), Include.journals).getJournals();
            List<Journal> data =journals.stream().toList();

            ArrayList<Date> list=new ArrayList<>();
            for (Journal dat : journals.stream().toList()) {
                list.add(dat.getCreatedOn());
            }
            Collections.sort(list);
            Date date = list.getLast();
            return date;
        } catch (RedmineException e) {
            throw new RuntimeException(e);
        }

    }

    ArrayList<ArrayList<Object>> get_all_issue () {
        List<Issue> issues;
        try {
            issues = mgr.getIssueManager().getIssues("hello", null);
        } catch (RedmineException e) {
            throw new RuntimeException(e);
        }
        ArrayList<ArrayList<Object>> all = new ArrayList<ArrayList<Object>>();
        for (Issue issue : issues) {
            ArrayList<Object> singleList = new ArrayList<Object>();
            singleList.add(issue.getSubject());
            singleList.add(issue.getPriorityText());
            singleList.add(issue.getAssigneeName());
            singleList.add(get_email(issue.getAssigneeId()));
            singleList.add(issue.getAuthorName());
            singleList.add(issue.getAuthorId());
            singleList.add(get_email(issue.getAuthorId()));
            singleList.add(issue.getUpdatedOn());
            singleList.add(issue.getDueDate());
            singleList.add(get_note_date(issue));

            all.add(singleList);
        }

        return  all;
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        if (date1== null || date2 == null) {
            return 9_999_999_999_999L;
        }
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    void check_old () {
        ArrayList<ArrayList<Object>> old = new ArrayList<>();
        for (ArrayList<Object> issue :this.now_all)  {
            if (7 <= getDateDiff((Date) issue.get(9), new Date(), TimeUnit.DAYS)) {
                old.add(issue);
            }
        }
        this.new_old_issue=old;

    }

    String get_email(Integer id)  {
        String email = "";
        if (id != null) {
            try {
                email=this.mgr.getUserManager().getUserById(id).getMail();
            } catch (RedmineException e) {
                throw new RuntimeException(e);
            }
        }
        return email;
    }

    void check_deadline () {
        ArrayList<ArrayList<Object>> old = new ArrayList<>();
        for (ArrayList<Object> issue :this.now_all)  {
            if (2 >= getDateDiff( new Date(), (Date) issue.get(8), TimeUnit.DAYS)) {
                old.add(issue);
            }
        }
        this.new_dealines=old;
    }

    void get_difference () {
        this.now_all=get_all_issue();
        check_old();
        check_deadline();

        ArrayList<ArrayList<Object>> diff_old=new ArrayList<>();
        diff_old.addAll(this.new_old_issue);

        diff_old.removeAll(this.old_issue);
        this.diff_old=diff_old;
        this.old_issue=new_old_issue;


        ArrayList<ArrayList<Object>> diff=new ArrayList<>();

        diff.addAll(this.now_all);

        diff.removeAll(this.old_all);
        this.old_all=this.now_all;
        this.diff=diff;


        ArrayList<ArrayList<Object>> diff_deadline=new ArrayList<>();
        diff_deadline.addAll(this.new_dealines);

        diff_deadline.removeAll(this.dealines);
        diff_deadline.removeAll(diff);

        this.diff_deadlines=diff_deadline;
        this.dealines=new_dealines;

    }
}
