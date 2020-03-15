package com.cplanet.toring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ToringApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ToringApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
    {
        return builder.sources(ToringApplication.class);
    }
}
