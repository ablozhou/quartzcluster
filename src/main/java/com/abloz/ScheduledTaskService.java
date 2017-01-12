/**
 * ScheduledTaskService.java
 * @Author zhouhh
 * 版权所有 (c) 2017. 北京荣之联科技股份有限公司. 保留所有权利.
 */
package com.abloz;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
/**
 * 
 * ScheduledTaskService
 * @author zhouhh
 * @Date 2017年1月11日
 */
@Service
public class ScheduledTaskService implements CommandLineRunner {
	Logger log = LoggerFactory.getLogger(ScheduledTaskService.class);

	@Autowired
	@Qualifier("schedulerFactoryBean")
	SchedulerFactoryBean schedulerFactoryBean;
	/*
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) throws Exception {
		log.debug("ScheduledTaskService running!");
		
		//add job runs every 10 seconds.
		try {
			addJob("hello","hellogroup",5,"0/10 * * * * ?", PrintNowJob.class, new HashMap<String,Object>());
		} catch (SchedulerException e) {
			log.warn(e.getMessage());
		}
		
	}
	public void addJob(String jobName, String groupName, int triggerPriority,String cronExpression,
			Class<? extends Job> jobClass,Map<String,Object> map) 
					throws SchedulerException{
		String tiggerName = jobName+"_trigger";
		String jobGroupName = groupName;
		
		//job
		JobDetail jobDetail = JobBuilder.newJob(jobClass)
				.withIdentity(jobName,jobGroupName)
				.build();
		
		//传入参数
		jobDetail.getJobDataMap().putAll(map);
		
		//任务触发器
		Trigger trigger = TriggerBuilder.newTrigger()
			.withIdentity(tiggerName)
			.startAt(new Date())
			.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
			.withPriority(triggerPriority)
			.build();
		
		//scheduler
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.scheduleJob(jobDetail,trigger);
		scheduler.start();
		
		log.info("Successed to add a scheduler task: "+tiggerName 
				+",cronexpress is " + cronExpression);
	}
}
