package com.ming.happyclub.common.vo;

import com.ming.happyclub.common.enums.ResponseStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @description: 消息体
 * @date 2023/11/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message<T>{

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Head{
        @ApiModelProperty(value = "响应编码, 200表示成功返回，其他异常返回", required = true, example = "200")
        private Integer respCode;

        @ApiModelProperty(value = "返回消息", required = true, example = "成功")
        private String respMsg;
    }


    @ApiModelProperty(value = "响应头", required = true)
    private Head head;


    @ApiModelProperty(value = "响应体", required = true)
    private T body;


    /**
     * @description: 成功的消息 可自定义成功返回码
     * @date: 2023/11/17
     */
    public static <T> Message success(ResponseStatusEnum responseStatusEnum, T t) {
        return out(responseStatusEnum.getCode(), responseStatusEnum.getDesc(),t);
    }

    /**
     * @description: 成功的消息
     * @date: 2023/11/17
     */
    public static <T> Message success(T t) {
        return out(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getDesc(),t);
    }

    /**
     * @description: 失败的消息 可自定义失败返回码
     * @date: 2023/11/17
     */
    // 失败的消息
    public static Message error(ResponseStatusEnum responseStatusEnum, String message) {
        return out(responseStatusEnum.getCode(),message,null);
    }

    /**
     * @description: 失败的消息
     * @date: 2023/11/17
     */
    public static Message error(String message){
        return out(ResponseStatusEnum.ERROR.getCode(), message,null);
    }

    /**
     * @description: Message封装体
     * @date: 2023/11/17
     */
    public static <T> Message out(Integer code, String msg, T t) {
        Head head = Head.builder()
                .respCode(code)
                .respMsg(msg)
                .build();

        return Message.builder()
                .head(head)
                .body(t)
                .build();
    }

}
