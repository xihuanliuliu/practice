package com.ai.aif.gitai.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJonCornTrigger implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " | 任务被执行了");
    }

}
