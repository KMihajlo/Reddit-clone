package com.mkraguje.redditclone;

import com.mkraguje.redditclone.model.Comment;
import com.mkraguje.redditclone.model.Link;
import com.mkraguje.redditclone.repository.CommentRepository;
import com.mkraguje.redditclone.repository.LinkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RedditCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository){
		return args -> {
			Link link = new Link("Getting started with spring boot 2", "https://www.danvega.dev/docs/spring-boot-2-docs");
			linkRepository.save(link);

			Comment comment = new Comment("This link is awesome!!", link);
			commentRepository.save(comment);

			link.addComment(comment);

			System.out.println("Inserted a link and comment");
			System.out.println("---------------------------------------------------");
		};
	}
}
