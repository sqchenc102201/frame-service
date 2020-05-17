package com.it.frame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.it.frame.mapper")
public class FrameServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameServiceApplication.class, args);
    }

}
