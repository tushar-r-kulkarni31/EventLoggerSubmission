package com.app.event.eventlogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.app.event", "com.app.event.service"})
@EntityScan("com.app.event.entity")
@EnableJpaRepositories(basePackages = {"com.app.event.repository"})
public class EventLoggerApplication{

    public static void main(String[] args) {
        SpringApplication.run(EventLoggerApplication.class, args);
    }

}
