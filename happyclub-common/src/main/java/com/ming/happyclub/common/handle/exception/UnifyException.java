package com.ming.happyclub.common.handle.exception;

import com.ming.happyclub.common.vo.response.Message;

/**
 * @version 1.0
 * @description: 统一异常类
 * @date 2023/11/17
 * @Replenishment: TODO
 */
public class UnifyException extends RuntimeException{
    public UnifyException() {
    }

    public UnifyException(String message) {
        super(message);
    }
}
