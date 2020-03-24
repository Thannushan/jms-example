package com.example.SpringActiveMQDemo;

import com.example.publisher.Publisher;
import com.example.util.JsonHelper;
import org.apache.activemq.util.StringToListOfActiveMQDestinationConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class SpringActiveMqDemoApplication implements ApplicationRunner{

	private static final Logger logger = LogManager.getFormatterLogger();

	@Autowired
	Publisher publisher;

	@Autowired
	JsonHelper jsonHelper;

	public static void main(String[] args) {
		SpringApplication.run(SpringActiveMqDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("[INFO] - Started Application " + this.getClass());
	}
}
