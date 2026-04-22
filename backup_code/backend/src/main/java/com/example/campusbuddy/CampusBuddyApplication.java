package com.example.campusbuddy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@MapperScan("com.example.campusbuddy.mapper") //扫描 Mapper 接口生成代理对象
@Validated //启用方法级别的参数校验
public class CampusBuddyApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampusBuddyApplication.class, args);
    }
}