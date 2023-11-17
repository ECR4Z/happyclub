package com.ming.happyclub.common.handle;

import com.ming.happyclub.common.enums.ResponseEnum;
import com.ming.happyclub.common.handle.exception.UnifyException;
import com.ming.happyclub.common.vo.response.Message;
import com.ming.happyclub.common.vo.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @version 1.0
 * @description: 全局异常处理类
 * @date 2023/11/17
 * @Replenishment: 注意多模块下，需要相关模块下配置scanBasePackages=com.ming.happyclub.common
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Message handleException(Exception e){
        if(e instanceof UnifyException){
            //处理自定义异常类 业务逻辑
            log.info(String.format("业务异常，异常信息：%s",e.getMessage()));
            return R.error(ResponseEnum.ERROR_EXCEPTION,e.getMessage());
        }
        return R.error(e.getMessage());
    }
}
