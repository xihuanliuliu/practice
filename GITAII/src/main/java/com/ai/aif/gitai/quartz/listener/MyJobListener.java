package com.ai.aif.gitai.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MyJobListener implements JobListener {

    @Override
    public String getName() {
        String name = this.getClass().getSimpleName();
        System.out.println("当前JobListener的名称为：" + name);
        return name;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String name = context.getJobDetail().getJobClass().getName();
        System.out.println("当前Job的名称为：" + name + "，JobDetail将要被执行了...");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        String name = context.getJobDetail().getJobClass().getName();
        System.out.println("当前Job的名称为：" + name + "，JobDetail将要被执，但被TriggerListener否决...");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        String name = context.getJobDetail().getJobClass().getName();
        System.out.println("当前Job的名称为：" + name + "，JobDetail执行完成了...");
    }

}
