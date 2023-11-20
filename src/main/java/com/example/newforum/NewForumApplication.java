package com.example.newforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NewForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewForumApplication.class, args);
    }

}
