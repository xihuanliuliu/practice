package com.ai.aif.gitai.quartz.listener;

import com.ai.aif.gitai.quartz.SimpleTriggerImpl;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.Date;

public class HelloSchedulerDemoListener {

    public static void main(String[] args) throws SchedulerException {
        //通过调度器工厂构建调度器实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //通过JobBuilder构建JobDetail
        JobDetail jobDetail = JobBuilder.newJob(HelloJobToListener.class)
                .withIdentity("job1", "group1")
                .build();
        //通过TriggerBuilder构建Trigger
//        CronTrigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("trigger1", "group1")
//                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("3 * * * * ?"))
//                .build();
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                //设置触发器唯一实例名称和触发器的组名
                .withIdentity("trigger1", "group1")
                //执行计划，每五秒执行一次
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                //立即执行
                .startNow()
                //构建实例
                .build();
        //调度器绑定JobDetail和Trigger
        scheduler.scheduleJob(jobDetail, trigger);
        //绑定job监听
        //1.全局绑定，所有的job在被调度的时候都会被监听
        //scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());
        //2.局部监听，用来监听指定的job
        scheduler.getListenerManager().addJobListener(new MyJobListener(),
                //jobKey中的name参数和group参数对应的就是任务实例【JobDetail】的name和group
                KeyMatcher.keyEquals(JobKey.jobKey("job1","group1")));
        //开启调度
        scheduler.start();
    }

}
