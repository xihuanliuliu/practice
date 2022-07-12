package com.ai.aif.gitai.quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJobDataMap2 implements Job {

    private String message;
    private String username;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //通过JobExecutionContext获取JobDetail
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        String jobDetailName = jobKey.getName();
        String jobDetailGroup = jobKey.getGroup();
        String jobClazzNameJobDetail = jobKey.getClass().getName();
        System.out.println("任务实例的唯一标识名称：" + jobDetailName);
        System.out.println("任务实例的组名：" + jobDetailGroup);
        System.out.println("任务实例绑定的任务类信息：" + jobClazzNameJobDetail);
        System.out.println("-----------------------------------------");
        //通过JobExecutionContext获取Trigger
        Trigger trigger = jobExecutionContext.getTrigger();
        TriggerKey triggerKey = trigger.getKey();
        String triggerKeyName = triggerKey.getName();
        String triggerKeyGroup = triggerKey.getGroup();
        String triggerClazzName = triggerKey.getClass().getName();
        System.out.println("触发器的唯一标识名称：" + triggerKeyName);
        System.out.println("触发器的组名：" + triggerKeyGroup);
        System.out.println("触发器绑定的任务类信息：" + triggerClazzName);
        System.out.println("-----------------------------------------");
        //通过JobExecutionContext
        String jobClazzName = jobExecutionContext.getClass().getName();
        System.out.println("job类相关的信息："+jobClazzName);
        System.out.println("-----------------------------------------");
        //获取JobDetail中JobDataMap中的message内容
        //JobDataMap jobDataMap = jobDetail.getJobDataMap();
        //String message = jobDataMap.getString("message");
        System.out.println("从JobDetail-JobDataMap中或到的message内容为：" + message);
        //获取Trigger中JobDataMap中的username内容
        //JobDataMap triggerJobDataMap = trigger.getJobDataMap();
        //String username = triggerJobDataMap.getString("username");
        System.out.println("从Trigger-JobDataMap中获取到的username内容为：" + username);
        System.out.println("-----------------------------------------");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " | 任务被执行了");
    }

}
