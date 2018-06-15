package com.spring.service;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import com.spring.quartz.job.SampleJob;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuartzService {
	public void schedule() {
		try {
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("jobKey", "jobValue");
			
			JobDetail job = JobBuilder.newJob(SampleJob.class)
					.setJobData(jobDataMap)
					.withIdentity("sampleJobKey", "JobGroup1")
					.build();
			
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("triggerName", "triggerGroup1")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
		
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
			
		} catch (SchedulerException e) {
			log.error("Exception : ", e);
		} 
	}
}
