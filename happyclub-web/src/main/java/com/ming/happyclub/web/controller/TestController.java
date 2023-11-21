package com.ming.happyclub.web.controller;

import com.ming.happyclub.web.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @description: 测试控制器
 * @date 2023/11/20
 * @Replenishment: 测试统一异常
 */
@Api(value = "测试控制器", tags = "测试")
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping("/exception")
    @ApiOperation(value = "测试",notes = "测试备注")
    public String test(@ApiParam(value = "num") @RequestParam(value = "num") int num){
        return testService.test(num);
    }
}
