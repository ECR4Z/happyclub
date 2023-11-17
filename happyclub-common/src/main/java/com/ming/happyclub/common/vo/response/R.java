package com.ming.happyclub.common.vo.response;

import com.ming.happyclub.common.enums.ResponseEnum;
/**
 * @version 1.0
 * @description: 响应Response封装
 * @date 2023/11/17
 */
public class R {
    /**
     * @description: 成功的消息 可自定义成功返回码
     * @date: 2023/11/17
     */
    public static <T> Message success(ResponseEnum responseEnum, T t) {
        return Message.out(responseEnum.getCode(),responseEnum.getDesc(),t);
    }

    /**
     * @description: 成功的消息
     * @date: 2023/11/17
     */
    public static <T> Message success(T t) {
        return Message.out(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc(),t);
    }

    /**
     * @description: 失败的消息 可自定义失败返回码
     * @date: 2023/11/17
     */
    // 失败的消息
    public static Message error(ResponseEnum responseEnum, String message) {
        return Message.out(responseEnum.getCode(),message,null);
    }

    /**
     * @description: 失败的消息
     * @date: 2023/11/17
     */
    public static Message error(String message){
        return Message.out(ResponseEnum.ERROR.getCode(), message,null);
    }
}
