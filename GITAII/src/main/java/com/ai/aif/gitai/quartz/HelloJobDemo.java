package com.ai.aif.gitai.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class HelloJobDemo implements Job {

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
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " | 任务被执行了");

    }
}
