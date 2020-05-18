package com.it.frame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.it.frame.mapper")
@EnableScheduling
public class FrameServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameServiceApplication.class, args);
    }

}
