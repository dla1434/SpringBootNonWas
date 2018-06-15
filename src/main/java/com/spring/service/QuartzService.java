package com.spring.service;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.quartz.job.SampleJob;
import com.spring.quartz.job.SimpleJob;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuartzService {
	@Autowired
	Scheduler scheduler;

	public void schedule() {
		try {
			//new StdSchedulerFactory("quartz.properties")로 이동
			//classpath에서 해당 인자명으로 파일 검색하여 quartz 설정을 인식한다.
//			System.setProperty(StdSchedulerFactory.PROPERTIES_FILE, "quartz.properties");
			
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("jobDataKey", "jobDataValue");
			
			JobKey sampleJobKey = new JobKey("sampleJobKey", "Group1");
			JobDetail sampleJob = JobBuilder.newJob(SampleJob.class)
					.setJobData(jobDataMap)
//					.withIdentity("sampleJobKey", "JobGroup1")
					.withIdentity(sampleJobKey)
					.build();
			
			JobKey simpleJobKey = new JobKey("simpleJobKey", "Group1");
			JobDetail simpleJob = JobBuilder.newJob(SimpleJob.class)
					.setJobData(jobDataMap)
					.withIdentity(simpleJobKey)
					.build();
			
//			Trigger trigger = TriggerBuilder.newTrigger()
//					.withIdentity("sampleTriggerName", "Group1")
//					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
			
			Trigger sampleTrigger = TriggerBuilder
				.newTrigger()
				.withIdentity("sampleTriggerName", "Group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
				.build();
			
			Trigger simpleTrigger = TriggerBuilder
					.newTrigger()
					.withIdentity("simpleTriggerName", "Group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
					.build();
		
// 			Scheduler scheduler = new StdSchedulerFactory("quartz.properties").getScheduler();
// 			scheduler.start();
			scheduler.scheduleJob(sampleJob, sampleTrigger);
			scheduler.scheduleJob(simpleJob, simpleTrigger);
			
		} catch (SchedulerException e) {
			log.error("Exception : ", e);
		} 
	}
}
