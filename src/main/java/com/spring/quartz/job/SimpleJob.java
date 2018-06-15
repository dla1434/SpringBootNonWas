package com.spring.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SimpleJob implements Job{
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("simpleJob execute!!");
	}
}
