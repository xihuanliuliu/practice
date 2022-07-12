package com.ai.aif.gitai.quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@PersistJobDataAfterExecution
public class HelloJobCount implements Job {

    private Integer count;

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //通过JobExecutionContext获取JobDetail
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        count++;
        jobDetail.getJobDataMap().put("count",count);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " | 任务被执行了");
        System.out.println("当前count的值为：" + count);
    }
}
