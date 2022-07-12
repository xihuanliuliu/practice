package com.ai.aif.gitai.quartz.listener;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJobToListener implements Job {

    public void execute(JobExecutionContext context) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " | 任务被执行了");
    }

}
