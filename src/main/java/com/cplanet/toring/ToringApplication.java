package com.cplanet.toring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ToringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToringApplication.class, args);
    }
}
