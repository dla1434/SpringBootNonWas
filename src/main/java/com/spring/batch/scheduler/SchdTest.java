package com.spring.batch.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SchdTest {
//	@Scheduled(cron = "*/10 * * * * *")
	public void run() {
		System.out.println("test");
		log.info("hello2----");
	}
}
