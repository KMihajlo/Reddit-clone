package com.mkraguje.redditclone;

import com.mkraguje.redditclone.config.RedditCloneConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableConfigurationProperties(RedditCloneConfiguration.class)
public class RedditCloneApplication {

	@Autowired
	private RedditCloneConfiguration redditCloneConfiguration;

	private static final Logger log = LoggerFactory.getLogger(RedditCloneApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneApplication.class, args);
		System.out.println("Welcome to Reddit-clone");
	}

	@Bean
	@Profile("development")
	CommandLineRunner commandLineRunner(){
		return args -> {
			System.out.println("THIS IS IN " + redditCloneConfiguration.getStage1() + " stage");
		};
	}

	@Bean
	@Profile("production")
	CommandLineRunner lineRunner(){
		return args -> {
			System.out.println("THIS IS IN " + redditCloneConfiguration.getStage2() + " stage");
		};
	}



	@Bean
	CommandLineRunner runner(){
		return args -> {
			log.error("CommandLineRunner.run();");
			log.warn("CommandLineRunner.run();");
			log.info("CommandLineRunner.run();");
			log.debug("CommandLineRunner.run();");
			log.trace("CommandLineRunner.run();");
		};
	}
}
