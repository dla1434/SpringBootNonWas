package com.spring.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
	@Bean
	public Scheduler schedulerFactory() throws SchedulerException{
		Scheduler scheduler = new StdSchedulerFactory("quartz.properties").getScheduler();
		scheduler.start();
		
		return scheduler;
	}
}
