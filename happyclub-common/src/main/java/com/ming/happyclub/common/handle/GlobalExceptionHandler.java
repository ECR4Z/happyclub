package com.ming.happyclub.common.handle;

import com.ming.happyclub.common.enums.ResponseStatusEnum;
import com.ming.happyclub.common.handle.exception.UnifyException;
import com.ming.happyclub.common.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @version 1.0
 * @description: 全局异常处理类
 * @date 2023/11/17
 * @Replenishment:
 * 注意多模块下，需要相关模块下配置 @SpringBootApplication(scanBasePackages = "com.ming.happyclub.common")
 * 同时需要在maven里引用一下该模块
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Message handleException(Exception e){
        if(e instanceof UnifyException){
            // 处理自定义异常类 业务逻辑
            log.warn(String.format("触发自定义异常:%s",e.getMessage()));
            return Message.error(ResponseStatusEnum.ERROR_EXCEPTION,e.getMessage());
        }
        log.warn(String.format("业务异常,异常信息如下:%s",e.getMessage()));
        return Message.error(e.getMessage());
    }
}
