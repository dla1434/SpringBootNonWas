package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.service.HelloMessageService;

import lombok.extern.slf4j.Slf4j;

import static java.lang.System.exit;

@SpringBootApplication
@Slf4j
public class SpringBootConsoleApplication implements CommandLineRunner {

	@Autowired
	private HelloMessageService helloService;

	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		// SpringApplication.run(SpringBootConsoleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0) {
			log.info("args : {}", helloService.getMessage(args[0].toString()));
		} else {
			log.info("name : {}", helloService.getMessage());
		}

		exit(0);
	}
}