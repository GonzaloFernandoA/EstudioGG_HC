package com.estudioGG.hc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HcApplication implements CommandLineRunner {

    private Logger log = LoggerFactory.getLogger(HcApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HcApplication.class, args);

    }

    @Override
    public void run(String... args) {
        log.info("Hello World from Application EstudioGG Runner");

    }

}
