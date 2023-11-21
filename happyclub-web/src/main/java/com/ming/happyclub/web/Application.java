package com.ming.happyclub.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version 1.0
 * @description: API接口启动器
 * @date 2023/11/20
 * @Replenishment: 启动的时候需要导入公共模块和自己的模块
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
