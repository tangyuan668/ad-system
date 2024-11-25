package com.tjetc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = {"com.tjetc.dao"})
@SpringBootApplication
public class AdvertiserApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdvertiserApplication.class,args);
    }

}
