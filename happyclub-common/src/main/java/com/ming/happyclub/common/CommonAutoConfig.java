package com.ming.happyclub.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @description: 跨模块的Bean注册（公共拦截器）
 * @date 2023/11/21
 * @Replenishment: 此处配置好后会自动被SpringBoot启动器扫描到
 */
@Configuration
@ComponentScan(basePackages = {"com.ming.happyclub.common"})
public class CommonAutoConfig {
}
