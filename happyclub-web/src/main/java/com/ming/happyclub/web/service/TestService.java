package com.ming.happyclub.web.service;

import com.ming.happyclub.common.vo.Message;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @description: 测试服务
 * @date 2023/11/20
 * @Replenishment: 测试统一异常
 */
@Service
public class TestService {
    public String test(int num) {
       return String.valueOf(1 / num);
    }

    public Message res(){
        return Message.success("");
    }
}
