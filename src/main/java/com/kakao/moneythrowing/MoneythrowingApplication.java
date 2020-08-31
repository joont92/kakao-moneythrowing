package com.kakao.moneythrowing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class MoneythrowingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneythrowingApplication.class, args);
    }

}
