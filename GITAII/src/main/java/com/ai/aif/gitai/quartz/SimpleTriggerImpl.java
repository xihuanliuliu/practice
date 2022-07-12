package com.ai.aif.gitai.quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@PersistJobDataAfterExecution
public class SimpleTriggerImpl implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Trigger trigger = jobExecutionContext.getTrigger();
        JobKey jobKey = trigger.getJobKey();
        System.out.println("触发器名称：" + jobKey.getName() + " | 触发器组名：" + jobKey.getGroup() );
        System.out.println("触发器开始执行时间：" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(trigger.getStartTime()) + " | 触发器结束执行时间：" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(trigger.getEndTime()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " | 任务被执行了");
    }

}
