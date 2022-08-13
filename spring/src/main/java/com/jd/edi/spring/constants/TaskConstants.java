package com.jd.edi.spring.constants;

/**
 * description:常量类
 * createAuthor:songxl3
 * createDate:2017/5/4
 */
public class TaskConstants {

	public static final String QUARTZ_SCHEDULER_DEF = "quartzScheduler";
   	/**
   	 * 定时任务状态
   	 */
   	public static final Integer SCHEDULE_TRIGGER_STATUS_WAIT_ACTIVATE 	= 1;//待激活
   	public static final Integer SCHEDULE_TRIGGER_STATUS_RUNNING 		= 2;//运行中
   	public static final Integer SCHEDULE_TRIGGER_STATUS_STOPED 			= 3;//已停止

   	/**
   	 * quartz
   	 */
   	public static final String JOB_GROUP_NAME_DEF = "DEFAULT";
	public static final String TRIGGER_GROUP_NAME_DEF = "DEFAULT";
	public static final int QUARTZ_DEF_TRIGGER_PRIORITY = 5;

	/**
	 * 各个任务触发器名
	 */
	public static final String GIT_LAB_TRIGGER_NAME = "GitlabJobTrigger";

    private TaskConstants() {}

}
